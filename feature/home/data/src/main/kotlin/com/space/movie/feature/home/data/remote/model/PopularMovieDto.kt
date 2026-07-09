package com.space.movie.feature.home.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PopularMovieDto (
    @SerialName("id") val id:Int,
    @SerialName("title") val title: String,
    @SerialName("poster_path") val posterPath: String? = null,
    @SerialName("overview") val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("release_date") val releaseDate: String,
)