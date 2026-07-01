package com.space.movieapp.feature.home.presentation.mapper

import com.space.common.base.BaseMapper
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

class PopularMovieUiMapper : BaseMapper<PopularMovie,PopularMovieUI > {
    override fun map(input: PopularMovie): PopularMovieUI = with(input) {
        PopularMovieUI(
            id = id,
            title = title,
            posterPath = posterPath,
            releaseDate = releaseDate,
            genre = genre
        )
    }
}