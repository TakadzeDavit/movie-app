package com.space.movie.feature.home.data.remote.datasource.movie

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import retrofit2.Response

class PopularMovieRemoteDataSourceImpl(
    private val apiService: PopularMoviesApiService
) : PopularMovieRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): Response<PopularMovieResponseDto> {
        return apiService.getPopularMovies(page = page)
    }


}