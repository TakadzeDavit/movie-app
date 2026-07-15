package com.space.feature.favorites.domain.usecase

import com.space.feature.favorites.domain.repository.FavoritesRepository

class GetAllFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke() = favoritesRepository.getAllFavorites()
}