package com.space.movie.feature.home.domain.repository

import com.space.common.api_result.ApiResult
import com.space.movie.feature.home.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    fun getGenres() : Flow<ApiResult<List<Genre>>>
}