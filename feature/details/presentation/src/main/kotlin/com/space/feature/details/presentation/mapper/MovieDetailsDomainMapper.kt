package com.space.feature.details.presentation.mapper

import com.space.common.base.BaseMapper
import com.space.core.domain.model.PopularMovie
import com.space.feature.details.domain.model.MovieDetails

class MovieDetailsDomainMapper : BaseMapper<MovieDetails, PopularMovie> {
    override fun map(input: MovieDetails): PopularMovie {
        return PopularMovie(
            id = input.id,
            title = input.title,
            genreIds = emptyList(),
            posterPath = input.posterUrl,
            releaseDate = input.year.toString(),
            genre = input.genre,
            isFavorite = true
        )
    }
}