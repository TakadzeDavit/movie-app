package com.space.feature.favorites.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.database.entity.FavoriteMovieEntity
import com.space.feature.favorites.domain.model.FavoriteMovie

class ToDomainMapper : BaseMapper<FavoriteMovieEntity, FavoriteMovie> {
    override fun map(input: FavoriteMovieEntity): FavoriteMovie {
        return FavoriteMovie(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genre = input.genre
        )
    }
}