package com.space.feature.favorites.domain.usecase

import com.space.feature.favorites.domain.repository.FavoritesRepository

class DeleteByIdUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(userId: Int) =
        favoritesRepository.removeFromFavorites(userId)
}