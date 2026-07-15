package com.space.movie.feature.home.data.remote.datasource.filter

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import retrofit2.Response

interface FilterMoviesRemoteDataSource {
    suspend fun getFilteredMovies(genreId: Int, page: Int): Response<PopularMovieResponseDto>
}