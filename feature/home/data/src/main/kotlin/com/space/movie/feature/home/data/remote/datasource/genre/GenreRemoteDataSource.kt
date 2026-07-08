package com.space.movie.feature.home.data.remote.datasource.genre

import com.space.movie.feature.home.data.model.genre.GenreListResponseDto
import retrofit2.Response

interface GenreRemoteDataSource {
    suspend fun getGenres() : Response<GenreListResponseDto>
}