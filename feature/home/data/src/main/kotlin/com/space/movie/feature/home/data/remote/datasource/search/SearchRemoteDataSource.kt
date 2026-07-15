package com.space.movie.feature.home.data.remote.datasource.search

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import retrofit2.Response

interface SearchRemoteDataSource {
    suspend fun searchMovies(
        query: String,
        page: Int
    ): Response<PopularMovieResponseDto>
}