package com.space.core.domain.usecase

import com.space.core.domain.repository.FavoritesRepository

class GetAllFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke() = favoritesRepository.getAllFavorites()
}