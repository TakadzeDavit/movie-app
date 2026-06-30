package com.space.movie.feature.home.data.repository

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.movie.feature.home.data.mapper.GenreMapper
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import com.space.movie.feature.home.domain.model.Genre
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.flow.Flow

class GenresRepositoryImpl(
    private val genreMapper: GenreMapper,
    private val responseHandler: ResponseHandler,
    private val genresApiService: GenresApiService
) : GenresRepository {
    override fun getGenres(): Flow<ApiResult<List<Genre>>> {
        return responseHandler.apiCall {
            genresApiService.getGenres()
        }.mapApiResult { dtoGenres ->
            dtoGenres.map(genreMapper::map)
        }
    }
}