package com.space.movie.feature.home.domain.usecase

import com.space.common.api_result.ApiResult
import com.space.movie.feature.home.domain.model.PopularMoviePage
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: PopularMoviesRepository
) {
    operator fun invoke(): Flow<ApiResult<PopularMoviePage>> = repository.getMovies()
}