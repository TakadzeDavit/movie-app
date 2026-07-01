package com.space.movie.feature.home.data.remote.apiservice

import com.space.movie.feature.home.data.remote.model.genre.GenreListResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GenresApiService {
    @GET("genre/movie/list")
    suspend fun getGenres() : Response<GenreListResponseDto>
}