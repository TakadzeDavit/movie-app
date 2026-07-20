package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.usecase.genres.FilterMoviesUseCase
import com.space.movie.feature.home.domain.usecase.genres.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.SearchMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPopularMoviesUseCase(repository = get()) }
    factory { GetGenresUseCase(genresRepository = get()) }
    factory { SearchMoviesUseCase(repository = get()) }
    factory { FilterMoviesUseCase(filterMoviesRepository = get()) }
    factoryOf(::GetMoviesUseCase)
}