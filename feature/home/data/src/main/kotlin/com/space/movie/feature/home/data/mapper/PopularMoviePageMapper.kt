package com.space.movie.feature.home.data.mapper

import com.space.common.base.BaseMapper
import com.space.movie.feature.home.data.remote.model.PopularMovieDto
import com.space.movie.feature.home.data.remote.model.PopularMovieResponseDto
import com.space.movie.feature.home.domain.model.PopularMoviePage

class PopularMoviePageMapper(
    private val popularMovieDtoMapper: PopularMovieDtoMapper
) : BaseMapper<PopularMovieResponseDto,PopularMoviePage > {
    override fun map(input: PopularMovieResponseDto): PopularMoviePage = with(input) {
        PopularMoviePage(
            page = page,
            results = results.map(popularMovieDtoMapper::map),
            totalPages = totalPages,
            totalResults = totalResults
        )
    }
}