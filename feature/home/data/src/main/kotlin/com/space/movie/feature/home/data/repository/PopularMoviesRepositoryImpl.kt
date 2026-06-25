package com.space.movie.feature.home.data.repository

import com.space.common.ApiResult
import com.space.common.mapApiResult
import com.space.movie.feature.home.data.mapper.toDomain
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.data.remote.model.PopularMovieDto
import com.space.movie.feature.home.data.remote.model.PopularMovieResponseDto
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.model.PopularMoviePage
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val popularMoviesApi: PopularMoviesApiService
) : PopularMoviesRepository {
    override fun getMovies(): Flow<ApiResult<PopularMoviePage>> {
        return responseHandler.apiCall {
            popularMoviesApi.getPopularMovies()
        }.mapApiResult {
            it.toDomain()
        }
    }
}