package com.space.movie.feature.home.data.repository

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.core.database.dao.GenreDao
import com.space.movie.feature.home.data.mapper.EntityToDomainMapper
import com.space.movie.feature.home.data.mapper.GenreEntityMapper
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import com.space.movie.feature.home.data.remote.datasource.genre.GenreRemoteDataSource
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movieapp.core.model.Genre
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenresRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val genreEntityMapper: GenreEntityMapper,
    private val entityToDomainMapper: EntityToDomainMapper,
    private val genreDao: GenreDao,
    private val genreRemoteDataSource: GenreRemoteDataSource
) : GenresRepository {
    override fun getGenres(): Flow<ApiResult<List<Genre>>> = flow {
        val cached = genreDao.getAllGenres()

        if (cached.isNotEmpty()) {
            emit(ApiResult.Success(cached.map(entityToDomainMapper::map)))
        } else {
            responseHandler.apiCall {
                genreRemoteDataSource.getGenres()
            }.mapApiResult { dtoGenres ->
                val entities = dtoGenres.genres.map(genreEntityMapper::map)
                genreDao.insertGenres(entities)
                entities.map(entityToDomainMapper::map)
            }.collect { result ->
                emit(result)
            }
        }
    }
}