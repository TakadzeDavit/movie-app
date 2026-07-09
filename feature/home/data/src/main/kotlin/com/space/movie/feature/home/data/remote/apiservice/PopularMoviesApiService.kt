package com.space.movie.feature.home.data.remote.apiservice

import com.space.movie.feature.home.data.remote.model.PopularMovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesApiService{
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): Response<PopularMovieResponseDto>
}