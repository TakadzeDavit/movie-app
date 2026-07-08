package com.space.feature.details.data.mapper

import com.space.common.base.BaseMapper
import com.space.common.exception.toYear
import com.space.feature.details.data.model.MovieDetailsDto
import com.space.feature.details.domain.model.MovieDetails
import com.space.movieapp.core.network.BuildConfig
import java.util.Locale

class MovieDetailsMapper : BaseMapper<MovieDetailsDto, MovieDetails> {
    override fun map(input: MovieDetailsDto): MovieDetails {
        val hours = input.runtime / 60
        val minutes = input.runtime % 60

        val formattedMinutes = minutes.toString().padStart(2, '0')

        val formattedDuration = "${hours}h ${formattedMinutes}m"

        val firstGenre = input.genres.firstOrNull()?.name ?: ""

        return MovieDetails(
            id = input.id,
            title = input.title,
            overview = input.overview,
            posterUrl = input.posterPath?.let { "${BuildConfig.IMAGE_BASE_URL}$it" }.orEmpty(),
            genre = firstGenre,
            duration = formattedDuration,
            year = input.releaseDate.toYear().toIntOrNull() ?: 0,
            rating = String.format(Locale.US, "%.1f", input.voteAverage).toDouble()
        )
    }
}