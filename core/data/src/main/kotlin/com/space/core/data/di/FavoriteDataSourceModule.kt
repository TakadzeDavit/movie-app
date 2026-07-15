package com.space.core.data.di

import com.space.core.data.local.datasource.FavoritesLocalDataSourceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val favoriteDataSourceModule = module {
    singleOf(::FavoritesLocalDataSourceImpl) bind FavoritesLocalDataSourceImpl::class
}