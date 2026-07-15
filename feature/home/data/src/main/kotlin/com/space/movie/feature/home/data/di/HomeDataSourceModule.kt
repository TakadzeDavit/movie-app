package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.remote.datasource.filter.FilterMoviesRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.filter.FilterMoviesRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.genre.GenreRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.genre.GenreRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.movie.PopularMovieRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.movie.PopularMovieRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.search.SearchRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.search.SearchRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeDataSourceModule = module {
    singleOf(::FilterMoviesRemoteDataSourceImpl) bind FilterMoviesRemoteDataSource::class
    singleOf(::GenreRemoteDataSourceImpl) bind GenreRemoteDataSource::class
    singleOf(::PopularMovieRemoteDataSourceImpl) bind PopularMovieRemoteDataSource::class
    singleOf(::SearchRemoteDataSourceImpl) bind SearchRemoteDataSource::class
}