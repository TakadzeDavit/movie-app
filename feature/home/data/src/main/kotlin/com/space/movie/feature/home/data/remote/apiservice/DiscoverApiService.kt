package com.space.movie.feature.home.data.remote.apiservice

import com.space.movie.feature.home.data.remote.model.movie.PopularMovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverApiService {
    @GET("discover/movie")
    suspend fun filterMovies(
        @Query("with_genres") genreId: Int,
        @Query("page") page: Int,

    ): Response<PopularMovieResponseDto>
}