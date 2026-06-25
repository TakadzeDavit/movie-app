package com.space.movie.feature.home.data.mapper

import com.space.movie.feature.home.data.remote.model.PopularMovieDto
import com.space.movie.feature.home.data.remote.model.PopularMovieResponseDto
import com.space.movie.feature.home.domain.model.PopularMoviePage

fun PopularMovieResponseDto.toDomain() = PopularMoviePage(
    page = page,
    results = results.map(PopularMovieDto::toDomain),
    totalPages = totalPages,
    totalResults = totalResults
)