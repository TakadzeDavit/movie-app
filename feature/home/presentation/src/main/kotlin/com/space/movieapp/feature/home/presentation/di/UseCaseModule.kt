package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.usecase.genres.FilterMoviesUseCase
import com.space.movie.feature.home.domain.usecase.genres.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.SearchMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetMoviesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    single { GetPopularMoviesUseCase(repository = get()) }
    single { GetGenresUseCase(genresRepository = get()) }
    single { SearchMoviesUseCase(repository = get()) }
    single { FilterMoviesUseCase(filterMoviesRepository = get()) }
    singleOf(::GetMoviesUseCase)
}