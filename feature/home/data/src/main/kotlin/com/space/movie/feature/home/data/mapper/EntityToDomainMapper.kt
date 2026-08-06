package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.database.entity.GenreEntity
import com.space.core.domain.model.Genre

class EntityToDomainMapper : BaseMapper<GenreEntity, Genre> {
    override fun map(input: GenreEntity): Genre {
        return Genre(
            id = input.id,
            name = input.name
        )
    }
}