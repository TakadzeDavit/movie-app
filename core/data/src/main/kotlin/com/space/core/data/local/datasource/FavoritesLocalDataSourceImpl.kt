package com.space.core.data.local.datasource

import com.space.core.database.dao.FavoriteDao
import com.space.core.database.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

class FavoritesLocalDataSourceImpl(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {
    override fun getFavoriteMovieIds(): Flow<List<Int>> {
        return favoriteDao.getFavoriteMovieIds()
    }

    override fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>> {
        return favoriteDao.getAllFavoriteMovies()
    }

    override suspend fun insertFavorite(movie: FavoriteMovieEntity) {
        return favoriteDao.insertFavorite(movie = movie)
    }

    override suspend fun deleteFavoriteById(movieId: Int) {
        return favoriteDao.deleteFavoriteById(movieId = movieId)
    }
}