package com.space.feature.favorites.domain.repository

import com.space.feature.favorites.domain.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites() : Flow<List<FavoriteMovie>>
    suspend fun removeFromFavorites(movieId: Int)
}