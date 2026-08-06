package com.space.movie.feature.home.data.remote.datasource.filter

import com.space.movie.feature.home.data.model.movie.PopularMovieResponseDto
import com.space.movie.feature.home.data.remote.apiservice.DiscoverApiService
import retrofit2.Response

class FilterMoviesRemoteDataSourceImpl(
    private val apiService: DiscoverApiService
) : FilterMoviesRemoteDataSource {
    override suspend fun getFilteredMovies(
        genreId: Int,
        page: Int
    ): Response<PopularMovieResponseDto> {
        return apiService.filterMovies(
            genreId = genreId,
            page = page
        )
    }
}