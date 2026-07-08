package com.space.core.domain.repository

import com.space.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getAllFavorites() : Flow<List<PopularMovie>>
    suspend fun removeFromFavorites(movieId: Int)
    fun getFavoriteMovieIds() : Flow<List<Int>>
    suspend fun insertFavorite(movie: PopularMovie)
}