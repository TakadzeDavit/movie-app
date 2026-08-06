package com.space.core.domain.usecase

import com.space.core.domain.repository.FavoritesRepository

class IsFavoriteUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(movieId: Int) = repository.isMovieFavorite(movieId)
}