package com.space.feature.favorites.data.repository

import com.space.core.database.dao.FavoriteDao
import com.space.feature.favorites.data.mapper.ToDomainMapper
import com.space.feature.favorites.domain.model.FavoriteMovie
import com.space.feature.favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val favoriteDao: FavoriteDao,
    private val toDomainMapper: ToDomainMapper
) : FavoritesRepository {
    override fun getAllFavorites(): Flow<List<FavoriteMovie>> {
        return favoriteDao.getAllFavoriteMovies().map { entityList ->
            entityList.map { entity ->
                toDomainMapper.map(entity)
            }
        }
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        favoriteDao.deleteFavoriteById(movieId)
    }
}