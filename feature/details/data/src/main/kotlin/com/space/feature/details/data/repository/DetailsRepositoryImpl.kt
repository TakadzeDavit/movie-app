package com.space.feature.details.data.repository

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.feature.details.data.remote.api_service.DetailsApiService
import com.space.feature.details.data.mapper.MovieDetailsMapper
import com.space.feature.details.data.remote.datasource.DetailsRemoteDataSource
import com.space.feature.details.domain.model.MovieDetails
import com.space.feature.details.domain.repository.DetailsRepository
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.flow.Flow

class DetailsRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val movieMapper: MovieDetailsMapper,
    private val detailsRemoteDataSource: DetailsRemoteDataSource
) : DetailsRepository {
    override fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetails>> {
        return responseHandler.apiCall {
            detailsRemoteDataSource.getMovieDetails(movieId = movieId)
        }.mapApiResult { movieDto ->
            movieMapper.map(movieDto)
        }
    }
}