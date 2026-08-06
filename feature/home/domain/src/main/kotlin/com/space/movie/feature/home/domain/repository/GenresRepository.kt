package com.space.movie.feature.home.domain.repository

import com.space.common.api_result.ApiResult
import com.space.core.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    fun getGenres() : Flow<ApiResult<List<Genre>>>
}