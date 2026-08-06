package com.space.movie.feature.home.data.model.genre

import kotlinx.serialization.Serializable

@Serializable
data class GenreListResponseDto(
    val genres: List<GenreResponseDto>
)

@Serializable
data class GenreResponseDto(
    val id: Int,
    val name: String
)