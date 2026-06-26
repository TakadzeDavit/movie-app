package com.space.movieapp.feature.home.presentation.di

import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import org.koin.dsl.module

val homeUiModule = module {
    factory { PopularMovieUiMapper() }
}