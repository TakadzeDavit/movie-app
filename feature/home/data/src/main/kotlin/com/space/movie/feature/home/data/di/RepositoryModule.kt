package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.repository.FilterMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.GenresRepositoryImpl
import com.space.movie.feature.home.data.repository.PopularMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.SearchMoviesRepositoryImpl
import com.space.movie.feature.home.domain.repository.FilterMoviesRepository
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import com.space.movie.feature.home.domain.repository.SearchMoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<PopularMoviesRepository> {
        PopularMoviesRepositoryImpl(
            popularMoviesApi = get(),
            popularMovieDtoMapper = get(),
            genreDao = get()
        )
    }

    single<GenresRepository> {
        GenresRepositoryImpl(
            responseHandler = get(),
            genresApiService = get(),
            genreEntityMapper = get(),
            entityToDomainMapper = get(),
            genreDao = get(),
        )
    }
    
    single<SearchMoviesRepository> {
        SearchMoviesRepositoryImpl(
            apiService = get(),
            popularMovieDtoMapper = get(),
            genreDao = get(),
        )
    }

    single<FilterMoviesRepository> {
        FilterMoviesRepositoryImpl(
            apiService = get(),
            dtoMapper = get(),
            genreDao = get()
        )
    }
}