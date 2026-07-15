package com.space.core.data.di

import com.space.core.domain.repository.FavoritesRepository
import com.space.core.data.repository.FavoritesRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val favoritesRepositoryModule = module {
    singleOf(::FavoritesRepositoryImpl) bind FavoritesRepository::class
}