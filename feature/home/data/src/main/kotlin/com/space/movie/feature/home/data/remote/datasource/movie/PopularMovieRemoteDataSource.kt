package com.space.movie.feature.home.data.remote.datasource.movie

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Response<PopularMovieResponseDto>
}