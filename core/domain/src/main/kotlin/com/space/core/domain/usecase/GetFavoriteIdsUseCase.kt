package com.space.core.domain.usecase

import com.space.core.domain.repository.FavoritesRepository

class GetFavoriteIdsUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke() = repository.getFavoriteMovieIds()
}