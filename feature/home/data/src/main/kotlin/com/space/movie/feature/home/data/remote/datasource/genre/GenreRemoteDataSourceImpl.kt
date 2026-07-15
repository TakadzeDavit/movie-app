package com.space.movie.feature.home.data.remote.datasource.genre

import com.space.movie.feature.home.data.model.genre.GenreListResponseDto
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import retrofit2.Response

class GenreRemoteDataSourceImpl(
    private val apiService: GenresApiService
) : GenreRemoteDataSource {
    override suspend fun getGenres(): Response<GenreListResponseDto> {
        return apiService.getGenres()
    }
}