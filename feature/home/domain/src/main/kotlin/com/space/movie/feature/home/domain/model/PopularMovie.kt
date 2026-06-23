package com.space.movie.feature.home.domain.model

data class PopularMovie (
    val id:Int,
    val title: String,
    val posterPath: String? = null,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
)