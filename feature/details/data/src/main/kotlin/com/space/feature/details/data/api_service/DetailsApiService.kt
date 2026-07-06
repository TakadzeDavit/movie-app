package com.space.feature.details.data.api_service

import com.space.feature.details.data.model.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ) : Response<MovieDetailsDto>
}