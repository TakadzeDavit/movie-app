package com.space.core.domain.usecase

import com.space.core.domain.model.PopularMovie
import com.space.core.domain.repository.FavoritesRepository

class InsertFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    suspend operator fun invoke(movie: PopularMovie) = repository.insertFavorite(movie = movie)
}