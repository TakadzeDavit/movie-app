package com.space.movieapp.feature.home.presentation.mapper

import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

fun PopularMovie.toPresentation() = PopularMovieUI(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    releaseDate = releaseDate
)