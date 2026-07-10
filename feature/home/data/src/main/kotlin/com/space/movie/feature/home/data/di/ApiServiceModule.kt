package com.space.movie.feature.home.data.di

import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServiceModule = module {
    single<PopularMoviesApiService> { get<Retrofit>().create(PopularMoviesApiService::class.java) }
}