package com.space.movie.feature.home.domain.usecase

import com.space.common.api_result.ApiResult
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movieapp.core.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(
    private val genresRepository: GenresRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Genre>>> = genresRepository.getGenres()
}