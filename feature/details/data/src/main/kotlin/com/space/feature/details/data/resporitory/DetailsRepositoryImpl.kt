package com.space.feature.details.data.resporitory

import com.space.common.api_result.ApiResult
import com.space.common.mapper.mapApiResult
import com.space.feature.details.data.api_service.DetailsApiService
import com.space.feature.details.data.mapper.MovieDetailsMapper
import com.space.feature.details.domain.model.MovieDetails
import com.space.feature.details.domain.repository.DetailsRepository
import com.space.movieapp.core.network.apicall.ResponseHandler
import kotlinx.coroutines.flow.Flow

class DetailsRepositoryImpl(
    private val responseHandler: ResponseHandler,
    private val movieMapper: MovieDetailsMapper,
    private val detailsApiService: DetailsApiService
) : DetailsRepository {
    override fun getMovieDetails(movieId: Int): Flow<ApiResult<MovieDetails>> {
        return responseHandler.apiCall {
            detailsApiService.getMovieDetails(movieId = movieId)
        }.mapApiResult { movieDto ->
            movieMapper.map(movieDto)
        }
    }
}