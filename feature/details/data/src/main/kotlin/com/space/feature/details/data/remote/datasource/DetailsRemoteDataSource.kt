package com.space.feature.details.data.remote.datasource

import com.space.feature.details.data.model.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.Path

interface DetailsRemoteDataSource {
    suspend fun getMovieDetails(movieId: Int) : Response<MovieDetailsDto>
}