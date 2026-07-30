package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.mapper.EntityToDomainMapper
import com.space.movie.feature.home.data.mapper.GenreEntityMapper
import com.space.movie.feature.home.data.mapper.GenreMapper
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.DiscoverApiService
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.data.remote.datasource.filter.FilterMoviesRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.filter.FilterMoviesRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.genre.GenreRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.genre.GenreRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.movie.PopularMovieRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.movie.PopularMovieRemoteDataSourceImpl
import com.space.movie.feature.home.data.remote.datasource.search.SearchRemoteDataSource
import com.space.movie.feature.home.data.remote.datasource.search.SearchRemoteDataSourceImpl
import com.space.movie.feature.home.data.repository.FilterMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.GenresRepositoryImpl
import com.space.movie.feature.home.data.repository.PopularMoviesRepositoryImpl
import com.space.movie.feature.home.data.repository.SearchMoviesRepositoryImpl
import com.space.movie.feature.home.domain.di.HomeScope
import com.space.movie.feature.home.domain.repository.FilterMoviesRepository
import com.space.movie.feature.home.domain.repository.GenresRepository
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import com.space.movie.feature.home.domain.repository.SearchMoviesRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped
import retrofit2.Retrofit
import retrofit2.create

val homeDataModule = module {
    scope<HomeScope> {
        // api service
        scoped<PopularMoviesApiService> { get<Retrofit>().create<PopularMoviesApiService>() }
        scoped<GenresApiService> { get<Retrofit>().create<GenresApiService>() }
        scoped<DiscoverApiService> { get<Retrofit>().create<DiscoverApiService>() }

        // mappers
        scoped<PopularMovieDtoMapper>()
        scoped<GenreMapper>()
        scoped<GenreEntityMapper>()
        scoped<EntityToDomainMapper>()

        // data sources
        scoped<FilterMoviesRemoteDataSourceImpl>() bind FilterMoviesRemoteDataSource::class
        scoped<GenreRemoteDataSourceImpl>() bind GenreRemoteDataSource::class
        scoped<PopularMovieRemoteDataSourceImpl>() bind PopularMovieRemoteDataSource::class
        scoped<SearchRemoteDataSourceImpl>() bind SearchRemoteDataSource::class

        // repositories
        scoped<PopularMoviesRepositoryImpl>() bind PopularMoviesRepository::class
        scoped<GenresRepositoryImpl>() bind GenresRepository::class
        scoped<SearchMoviesRepositoryImpl>() bind SearchMoviesRepository::class
        scoped<FilterMoviesRepositoryImpl>() bind FilterMoviesRepository::class
    }
}