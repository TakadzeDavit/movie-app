package com.space.core.domain.model

data class PopularMovie (
    val id: Int,
    val title: String,
    val genreIds: List<Int>,
    val posterPath: String?,
    val releaseDate: String,
    val genre: String = "",
    val isFavorite: Boolean = false
)