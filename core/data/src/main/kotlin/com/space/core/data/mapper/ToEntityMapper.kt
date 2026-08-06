package com.space.core.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.database.entity.FavoriteMovieEntity
import com.space.core.domain.model.PopularMovie

class ToEntityMapper : BaseMapper<PopularMovie, FavoriteMovieEntity> {
    override fun map(input: PopularMovie): FavoriteMovieEntity {
        return FavoriteMovieEntity(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            genre = input.genre,
            isFavorite = input.isFavorite
        )
    }
}