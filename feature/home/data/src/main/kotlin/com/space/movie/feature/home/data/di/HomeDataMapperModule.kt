package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.mapper.PopularMoviePageMapper
import org.koin.dsl.module

val homeDataMapperModule = module {
    factory { PopularMovieDtoMapper() }
    factory { PopularMoviePageMapper(popularMovieDtoMapper = get()) }
}