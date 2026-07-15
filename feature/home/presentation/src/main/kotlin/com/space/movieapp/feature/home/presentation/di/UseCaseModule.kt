package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.usecase.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPopularMoviesUseCase(repository = get()) }
    factory { GetGenresUseCase(genresRepository = get()) }
    factory { SearchMoviesUseCase(repository = get()) }
}