package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.repository.FilterMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.GenresRepositoryImpl
import com.space.movie.feature.home.data.repository.PopularMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.SearchMoviesRepositoryImpl
import com.space.movie.feature.home.domain.repository.FilterMoviesRepository
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import com.space.movie.feature.home.domain.repository.SearchMoviesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::PopularMoviesRepositoryImpl) bind PopularMoviesRepository::class
    singleOf(::GenresRepositoryImpl) bind GenresRepository::class
    singleOf(::SearchMoviesRepositoryImpl) bind SearchMoviesRepository::class
    singleOf(::FilterMoviesRepositoryImpl) bind FilterMoviesRepository::class
}