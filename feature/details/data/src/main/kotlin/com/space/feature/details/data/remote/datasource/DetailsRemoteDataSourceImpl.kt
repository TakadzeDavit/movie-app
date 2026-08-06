package com.space.feature.details.data.remote.datasource

import com.space.feature.details.data.model.MovieDetailsDto
import com.space.feature.details.data.remote.api_service.DetailsApiService
import retrofit2.Response

class DetailsRemoteDataSourceImpl(
    private val apiService: DetailsApiService
) : DetailsRemoteDataSource {
    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsDto> {
        return apiService.getMovieDetails(movieId = movieId)
    }
}