package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.repository.GenresRepositoryImpl
import com.space.movie.feature.home.data.repository.PopularMoviesRepositoryImpl
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PopularMoviesRepository> {
        PopularMoviesRepositoryImpl(
            popularMoviesApi = get(),
            popularMovieDtoMapper = get(),
        )
    }

    single<GenresRepository> {
        GenresRepositoryImpl(
            genreMapper = get(),
            responseHandler = get(),
            genresApiService = get()
        )
    }
}