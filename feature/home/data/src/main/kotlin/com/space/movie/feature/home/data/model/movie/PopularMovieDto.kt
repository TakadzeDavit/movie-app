package com.space.movie.feature.home.data.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PopularMovieDto(
    val id: Int,
    val title: String,
    @SerialName("genre_ids")
    val genreIds: List<Int> = emptyList(),
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String = "",
)