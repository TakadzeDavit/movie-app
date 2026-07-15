package com.space.core.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.database.entity.FavoriteMovieEntity
import com.space.core.domain.model.PopularMovie

class ToDomainMapper : BaseMapper<FavoriteMovieEntity, PopularMovie> {
    override fun map(input: FavoriteMovieEntity): PopularMovie {
        return PopularMovie(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genre = input.genre,
            genreIds = emptyList(),
            isFavorite = input.isFavorite,
        )
    }
}