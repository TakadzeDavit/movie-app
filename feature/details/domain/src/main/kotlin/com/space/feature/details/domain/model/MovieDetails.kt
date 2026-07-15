package com.space.feature.details.domain.model

data class MovieDetails (
    val id: Int,
    val title: String,
    val posterUrl: String,
    val rating: Double,
    val genre: String,
    val duration: String,
    val year: Int,
    val overview: String,
    val isFavorite: Boolean = false
)