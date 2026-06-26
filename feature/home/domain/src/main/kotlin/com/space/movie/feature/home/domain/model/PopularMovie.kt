package com.space.movie.feature.home.domain.model

data class PopularMovie (
    val id: Int,
    val title: String,
    val genreIds: List<Int>,
    val posterPath: String? = null,
    val releaseDate: String,
)