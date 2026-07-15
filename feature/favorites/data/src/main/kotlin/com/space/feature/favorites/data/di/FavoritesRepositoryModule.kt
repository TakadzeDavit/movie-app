package com.space.feature.favorites.data.di

import com.space.feature.favorites.data.repository.FavoritesRepositoryImpl
import com.space.feature.favorites.domain.repository.FavoritesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val favoritesRepositoryModule = module {
    singleOf(::FavoritesRepositoryImpl) bind FavoritesRepository::class

}