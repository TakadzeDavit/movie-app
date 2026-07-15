package com.space.movieapp.feature.home.presentation.mapper

import com.space.common.base.BaseMapper
import com.space.core.domain.model.PopularMovie
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

class MovieDomainMapper : BaseMapper<PopularMovieUI, PopularMovie> {
    override fun map(input: PopularMovieUI): PopularMovie {
        return PopularMovie(
            id = input.id,
            title = input.title,
            genreIds = emptyList(),
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genre = input.genre,
            isFavorite = true
        )
    }
}