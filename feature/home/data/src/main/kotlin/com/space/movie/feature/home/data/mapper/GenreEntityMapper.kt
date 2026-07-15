package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.core.database.entity.GenreEntity
import com.space.movie.feature.home.data.remote.model.genre.GenreResponseDto

class GenreEntityMapper : BaseMapper<GenreResponseDto, GenreEntity> {
    override fun map(input: GenreResponseDto): GenreEntity {
        return GenreEntity(
            id = input.id,
            name = input.name
        )
    }
}