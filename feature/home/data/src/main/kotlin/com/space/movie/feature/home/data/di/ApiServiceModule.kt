package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.remote.apiservice.DiscoverApiService
import com.space.movie.feature.home.data.remote.apiservice.GenresApiService
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.domain.usecase.GetGenresUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServiceModule = module {
    single<PopularMoviesApiService> { get<Retrofit>().create(PopularMoviesApiService::class.java) }
    single<GenresApiService> { get<Retrofit>().create(GenresApiService::class.java) }
    single<DiscoverApiService> { get<Retrofit>().create(DiscoverApiService::class.java) }
}