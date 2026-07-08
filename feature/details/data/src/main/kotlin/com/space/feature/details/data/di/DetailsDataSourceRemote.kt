package com.space.feature.details.data.di

import com.space.feature.details.data.remote.datasource.DetailsRemoteDataSource
import com.space.feature.details.data.remote.datasource.DetailsRemoteDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val detailsDataSourceRemote = module {
    singleOf(::DetailsRemoteDataSourceImpl) bind DetailsRemoteDataSource::class
}