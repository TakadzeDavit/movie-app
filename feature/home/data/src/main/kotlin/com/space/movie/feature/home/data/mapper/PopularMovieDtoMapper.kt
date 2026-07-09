package com.space.movie.feature.home.data.mapper

import com.space.movie.feature.home.data.remote.model.PopularMovieDto
import com.space.movie.feature.home.domain.model.PopularMovie

fun PopularMovieDto.toDomain() = PopularMovie(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    releaseDate = releaseDate
)