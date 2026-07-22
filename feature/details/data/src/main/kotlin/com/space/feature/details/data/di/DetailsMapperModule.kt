package com.space.feature.details.data.di

import com.space.feature.details.data.mapper.MovieDetailsMapper
import org.koin.dsl.module

val detailsMapperModule = module {
    single { MovieDetailsMapper() }
}