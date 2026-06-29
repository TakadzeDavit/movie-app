package com.space.movie.feature.home.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.space.common.api_result.NetworkError
import com.space.common.exception.PagingException
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.domain.model.PopularMovie
import java.io.IOException

class PopularMoviesPagingSource(
    private val popularMoviesApi: PopularMoviesApiService,
    private val popularMovieDtoMapper: PopularMovieDtoMapper
) : PagingSource<Int, PopularMovie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovie> {
        return try {
            val pageNumber = params.key ?: 1

            val response = popularMoviesApi.getPopularMovies(page = pageNumber)

            if (response.isSuccessful) {
                val moviesDto = response.body()?.results ?: emptyList()
                val domainMovies = moviesDto.map { popularMovieDtoMapper.map(it) }

                val prevKey = if (pageNumber > 1) pageNumber - 1 else null
                val nextKey = if (domainMovies.isEmpty()) null else pageNumber + 1

                LoadResult.Page(
                    data = domainMovies,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            } else {
                val errorType = when (response.code()) {
                    401 -> NetworkError.UNAUTHORIZED
                    404 -> NetworkError.NOT_FOUND
                    500 -> NetworkError.SERVER_ERROR
                    else -> NetworkError.UNKNOWN
                }
                LoadResult.Error(
                    PagingException(
                        errorType = errorType,
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
                    errorType = errorType,
                    message = e.message
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

