package com.space.movie.feature.home.domain.usecase

import com.space.movie.feature.home.domain.repository.SearchMoviesRepository

class SearchMoviesUseCase(
    private val repository: SearchMoviesRepository
) {
    operator fun invoke(query: String) = repository.getMovies(query)
}