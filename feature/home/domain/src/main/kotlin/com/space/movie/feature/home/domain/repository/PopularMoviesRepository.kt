package com.space.movie.feature.home.domain.repository

import com.space.common.ApiResult
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.model.PopularMoviePage
import kotlinx.coroutines.flow.Flow

interface PopularMoviesRepository {
    fun getMovies(): Flow<ApiResult<PopularMoviePage>>
}