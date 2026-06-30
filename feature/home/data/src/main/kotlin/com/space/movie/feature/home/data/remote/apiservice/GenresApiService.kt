package com.space.movie.feature.home.data.remote.apiservice

import com.space.movie.feature.home.data.remote.model.genre.GenreResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GenresApiService {
    @GET("genre/movie/list")
    fun getGenres() : Response<List<GenreResponseDto>>
}