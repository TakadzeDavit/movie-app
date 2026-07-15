package com.space.movie.feature.home.data.remote.paging.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.common.api_result.NetworkError
import com.space.common.exception.PagingException
import com.space.core.database.dao.GenreDao
import com.space.core.domain.model.PopularMovie
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movieapp.core.network.extension.toNetworkError
import java.io.IOException

class SearchPagingSource(
    private val apiService: PopularMoviesApiService,
    private val query: String,
    private val genreDao: GenreDao,
    private val dtoMapper: PopularMovieDtoMapper
) : PagingSource<Int, PopularMovie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        if (query.isBlank()) {
            return LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }

        return try {
            val currentPage = params.key ?: 1
            val response = apiService.searchMovies(query = query, page = currentPage)

            if (response.isSuccessful) {
                val dtoMovies = response.body()?.results ?: emptyList()
                val cachedGenres = genreDao.getAllGenres()
                val genreMap: Map<Int, String> = cachedGenres.associate { it.id to it.name }

                val domainMovies = dtoMovies.map { dto ->
                    dtoMapper.mapWithGenres(dto, genreMap)
                }

                LoadResult.Page(
                    data = domainMovies,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (domainMovies.isEmpty()) null else currentPage + 1
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

            LoadResult.Error(
                PagingException(
                    errorType = errorType, message = e.message
                )
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}