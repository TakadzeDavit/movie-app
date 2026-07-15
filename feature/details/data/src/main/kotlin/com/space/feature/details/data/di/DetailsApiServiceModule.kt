package com.space.feature.details.data.di

import com.space.feature.details.data.remote.api_service.DetailsApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsApiServiceModule = module {
    single<DetailsApiService> { get<Retrofit>().create(DetailsApiService::class.java) }
}