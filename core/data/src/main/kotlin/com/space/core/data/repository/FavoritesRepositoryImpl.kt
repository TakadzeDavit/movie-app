package com.space.core.data.repository

import com.space.core.data.local.datasource.FavoritesLocalDataSourceImpl
import com.space.core.database.dao.FavoriteDao
import com.space.core.domain.repository.FavoritesRepository
import com.space.core.data.mapper.ToDomainMapper
import com.space.core.data.mapper.ToEntityMapper
import com.space.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val toDomainMapper: ToDomainMapper,
    private val toEntityMapper: ToEntityMapper,
    private val favoritesLocalDataSourceImpl: FavoritesLocalDataSourceImpl
) : FavoritesRepository {
    override fun getAllFavorites(): Flow<List<PopularMovie>> {
        return favoritesLocalDataSourceImpl.getAllFavoriteMovies().map { entityList ->
            entityList.map { entity ->
                toDomainMapper.map(entity)
            }
        }
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        favoritesLocalDataSourceImpl.deleteFavoriteById(movieId)
    }

    override fun getFavoriteMovieIds(): Flow<List<Int>> {
        return favoritesLocalDataSourceImpl.getFavoriteMovieIds()
    }

    override suspend fun insertFavorite(movie: PopularMovie) {
        favoritesLocalDataSourceImpl.insertFavorite(movie = toEntityMapper.map(movie))
    }
}