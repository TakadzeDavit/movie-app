package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPopularMoviesUseCase(repository = get()) }
}