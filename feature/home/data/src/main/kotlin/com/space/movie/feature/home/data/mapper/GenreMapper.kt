package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.movie.feature.home.data.model.genre.GenreResponseDto
import com.space.movieapp.core.model.Genre

class GenreMapper : BaseMapper<GenreResponseDto, Genre> {
    override fun map(input: GenreResponseDto): Genre {
        return Genre(
            id = input.id,
            name = input.name
        )
    }
}