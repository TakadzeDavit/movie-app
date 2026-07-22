package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.mapper.EntityToDomainMapper
import com.space.movie.feature.home.data.mapper.GenreEntityMapper
import com.space.movie.feature.home.data.mapper.GenreMapper
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import org.koin.dsl.module

val homeDataMapperModule = module {
    single { PopularMovieDtoMapper() }
    single { GenreMapper() }
    single { GenreEntityMapper() }
    single { EntityToDomainMapper() }
}