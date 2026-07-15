package com.space.core.data.local.datasource

import com.space.core.database.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {
    fun getFavoriteMovieIds(): Flow<List<Int>>

    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    suspend fun insertFavorite(movie: FavoriteMovieEntity)

    suspend fun deleteFavoriteById(movieId: Int)
}