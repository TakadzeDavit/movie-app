package com.space.movieapp.feature.home.presentation.mapper

import com.space.common.extension.toYear
import com.space.core.domain.model.PopularMovie
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

class PopularMovieUiMapper {
    fun map(domainModel: PopularMovie, favoriteIds: Set<Int>): PopularMovieUI {
        return PopularMovieUI(
            id = domainModel.id,
            title = domainModel.title,
            posterPath = domainModel.posterPath,
            releaseDate = domainModel.releaseDate,
            genre = domainModel.genre,
            isFavorite = favoriteIds.contains(domainModel.id),
            year = domainModel.releaseDate.toYear()
        )
    }
}