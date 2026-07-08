package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.usecase.genres.FilterMoviesUseCase
import com.space.movie.feature.home.domain.usecase.genres.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.SearchMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPopularMoviesUseCase(repository = get()) }
    factory { GetGenresUseCase(genresRepository = get()) }
    factory { SearchMoviesUseCase(repository = get()) }
    factory { FilterMoviesUseCase(filterMoviesRepository = get()) }
}