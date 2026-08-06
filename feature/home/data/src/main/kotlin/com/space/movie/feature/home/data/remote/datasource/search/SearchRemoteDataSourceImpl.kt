package com.space.movie.feature.home.data.remote.datasource.search

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import retrofit2.Response

class SearchRemoteDataSourceImpl(
    private val apiService: PopularMoviesApiService
) : SearchRemoteDataSource{
    override suspend fun searchMovies(
        query: String,
        page: Int
    ): Response<PopularMovieResponseDto> {
        return apiService.searchMovies(query = query, page = page)
    }
}