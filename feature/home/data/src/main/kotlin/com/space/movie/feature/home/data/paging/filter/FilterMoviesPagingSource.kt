package com.space.movie.feature.home.data.paging.filter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.common.api_result.NetworkError
import com.space.common.exception.PagingException
import com.space.core.database.dao.GenreDao
import com.space.core.domain.model.PopularMovie
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.DiscoverApiService
import com.space.movie.feature.home.data.remote.datasource.filter.FilterMoviesRemoteDataSource
import com.space.movieapp.core.network.extension.toNetworkError
import java.io.IOException

class FilterMoviesPagingSource(
    private val remoteDataSource: FilterMoviesRemoteDataSource,
    private val genreId: Int,
    private val genreDao: GenreDao,
    private val dtoMapper: PopularMovieDtoMapper
) : PagingSource<Int, PopularMovie>() {
    private var cachedGenreMap: Map<Int, String>? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        return try {
            val currentPage = params.key ?: 1
            val response = remoteDataSource.getFilteredMovies(genreId = genreId, page = currentPage)

            if (response.isSuccessful) {
                val dtoMovies = response.body()?.results ?: emptyList()

                if (cachedGenreMap == null) {
                    val cachedGenres = genreDao.getAllGenres()
                    cachedGenreMap = cachedGenres.associate { it.id to it.name }
                }

                val currentGenreMap = cachedGenreMap ?: emptyMap()

                val domainMovies = dtoMovies.map { dto ->
                    dtoMapper.mapWithGenres(dto, currentGenreMap)
                }

                LoadResult.Page(
                    data = domainMovies,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (dtoMovies.isEmpty() || dtoMovies.size < params.loadSize)
                        null else currentPage + 1
                )
            } else {
                LoadResult.Error(
                    PagingException(
                        errorType = response.toNetworkError(),
                        message = response.errorBody()?.string()
                    )
                )
            }
        } catch (e: Exception) {
            val errorType = when (e) {
                is IOException -> NetworkError.NO_INTERNET
                else -> NetworkError.UNKNOWN
            }
            LoadResult.Error(PagingException(errorType = errorType, message = e.message))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}