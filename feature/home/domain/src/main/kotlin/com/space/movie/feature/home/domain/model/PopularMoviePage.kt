package com.space.movie.feature.home.domain.model

class PopularMoviePage (
    val page: Int,
    val results: List<PopularMovie>,
    val totalPages: Int,
    val totalResults: Int
)