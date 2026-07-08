package com.space.movie.feature.home.domain.usecase.movies

import androidx.paging.PagingData
import com.space.core.domain.model.PopularMovie
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase(
    private val repository: PopularMoviesRepository
) {
    operator fun invoke(): Flow<PagingData<PopularMovie>> = repository.getMovies()
}