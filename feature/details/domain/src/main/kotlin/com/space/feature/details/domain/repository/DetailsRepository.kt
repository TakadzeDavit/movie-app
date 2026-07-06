package com.space.feature.details.domain.repository

import com.space.common.api_result.ApiResult
import com.space.feature.details.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetails>>
}