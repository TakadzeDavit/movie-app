package com.space.movie.feature.home.data.repository

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.movie.feature.home.data.mapper.PopularMoviePageMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.domain.model.PopularMoviePage
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import com.space.movieapp.core.network.apicall.apiCall
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepositoryImpl(
    private val popularMoviesApi: PopularMoviesApiService,
    private val popularMoviePageMapper: PopularMoviePageMapper,
) : PopularMoviesRepository {
    override fun getMovies(): Flow<ApiResult<PopularMoviePage>> {
        return apiCall {
            popularMoviesApi.getPopularMovies()
        }.mapApiResult { dtoPage ->
            popularMoviePageMapper.map(dtoPage)
        }
    }
}