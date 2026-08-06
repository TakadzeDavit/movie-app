package com.space.movie.feature.home.domain.usecase.genres

import com.space.movie.feature.home.domain.repository.FilterMoviesRepository

class FilterMoviesUseCase(
    private val filterMoviesRepository: FilterMoviesRepository
) {
    operator fun invoke(genreId: Int) = filterMoviesRepository.getFilteredMovies(genreId)
}