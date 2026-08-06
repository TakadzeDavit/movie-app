package com.space.feature.details.data.di

import com.space.feature.details.data.mapper.MovieDetailsMapper
import com.space.feature.details.data.remote.api_service.DetailsApiService
import com.space.feature.details.data.remote.datasource.DetailsRemoteDataSource
import com.space.feature.details.data.remote.datasource.DetailsRemoteDataSourceImpl
import com.space.feature.details.data.repository.DetailsRepositoryImpl
import com.space.feature.details.domain.di.DetailsScope
import com.space.feature.details.domain.repository.DetailsRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped
import retrofit2.Retrofit
import retrofit2.create

val detailsDataModule = module {
    scope<DetailsScope> {
        scoped<DetailsApiService> { get<Retrofit>().create<DetailsApiService>() }
        scoped<MovieDetailsMapper>()
        scoped<DetailsRemoteDataSourceImpl>() bind DetailsRemoteDataSource::class
        scoped<DetailsRepositoryImpl>() bind DetailsRepository::class
    }
}