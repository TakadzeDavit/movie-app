package com.space.movieapp.feature.home.presentation.di

import com.space.movieapp.feature.home.presentation.mapper.MovieDomainMapper
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import com.space.movieapp.feature.home.presentation.vm.HomeVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeUiModule = module {
    factory { PopularMovieUiMapper() }
    factory { MovieDomainMapper() }
    viewModelOf(::HomeVM)
}