package com.space.core.domain.usecase

import com.space.core.domain.repository.FavoritesRepository

class DeleteByIdUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(userId: Int) =
        favoritesRepository.removeFromFavorites(userId)
}