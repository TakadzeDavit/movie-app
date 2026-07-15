package com.space.feature.details.data.di

import com.space.feature.details.data.repository.DetailsRepositoryImpl
import com.space.feature.details.domain.repository.DetailsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val detailsRepositoryModule = module {
    singleOf(::DetailsRepositoryImpl) bind DetailsRepository::class
}