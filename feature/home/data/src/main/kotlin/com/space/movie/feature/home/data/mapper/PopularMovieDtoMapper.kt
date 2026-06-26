package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.movie.feature.home.data.remote.model.PopularMovieDto
import com.space.movie.feature.home.domain.model.PopularMovie
import kotlinx.serialization.SerialName

class PopularMovieDtoMapper : BaseMapper<PopularMovieDto, PopularMovie> {
    override fun map(input: PopularMovieDto): PopularMovie = with(input) {
        PopularMovie(
            id = id,
            title = title,
            posterPath = posterPath,
            releaseDate = releaseDate,
            genreIds = genreIds
        )
    }
}