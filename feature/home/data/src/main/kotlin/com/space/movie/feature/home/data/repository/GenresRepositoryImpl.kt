package com.space.movie.feature.home.data.repository

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.core.database.dao.GenreDao
import com.space.movie.feature.home.data.mapper.EntityToDomainMapper
import com.space.movie.feature.home.data.mapper.GenreEntityMapper
import com.space.movie.feature.home.data.mapper.GenreMapper
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import com.space.movie.feature.home.domain.model.Genre
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GenresRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val genresApiService: GenresApiService,
    private val genreEntityMapper: GenreEntityMapper,
    private val entityToDomainMapper: EntityToDomainMapper,
    private val genreDao: GenreDao
) : GenresRepository {
    override fun getGenres(): Flow<ApiResult<List<Genre>>> = flow {
        val cached = genreDao.getAllGenres()

        if (cached.isNotEmpty()) {
            emit(ApiResult.Success(cached.map(entityToDomainMapper::map)))
        } else {
            responseHandler.apiCall {
                genresApiService.getGenres()
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