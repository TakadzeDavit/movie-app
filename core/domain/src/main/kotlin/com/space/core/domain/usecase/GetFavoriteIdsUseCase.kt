package com.space.core.domain.usecase

import com.space.core.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class GetFavoriteIdsUseCase(
    private val repository: FavoritesRepository
) {
    operator fun invoke(): Flow<Set<Int>> = repository
        .getFavoriteMovieIds()
        .map { it.toSet() }
        .distinctUntilChanged()
}