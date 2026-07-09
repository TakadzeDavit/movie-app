package com.space.movieapp.feature.home.presentation.model

data class PopularMovieUI (
    val id:Int,
    val title: String,
    val posterPath: String? = null,
    val overview: String,
    val voteAverage: Double,
    val releaseDate: String,
)