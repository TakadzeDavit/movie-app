package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.domain.model.PopularMovie
import com.space.movie.feature.home.data.BuildConfig
import com.space.movie.feature.home.data.remote.model.movie.PopularMovieDto

class PopularMovieDtoMapper : BaseMapper<PopularMovieDto, PopularMovie> {
    override fun map(input: PopularMovieDto): PopularMovie = with(input) {
        PopularMovie(
            id = id,
            title = title,
            posterPath = "${BuildConfig.IMAGE_BASE_URL}$posterPath",
            releaseDate = releaseDate,
            genreIds = genreIds
        )
    }

    fun mapWithGenres(input: PopularMovieDto, genreMap: Map<Int, String>): PopularMovie = with(input) {
        val firstGenreId = genreIds.firstOrNull()
        val genreString = genreMap[firstGenreId] ?: ""

        PopularMovie(
            id = id,
            title = title,
            posterPath = "${BuildConfig.IMAGE_BASE_URL}$posterPath",
            releaseDate = releaseDate,
            genreIds = genreIds,
            genre = genreString
        )
    }
}