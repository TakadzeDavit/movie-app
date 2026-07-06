package com.space.feature.favorites.domain.model

data class FavoriteMovie (
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String,
    val genre: String,
    val isFavorite: Boolean = true
)