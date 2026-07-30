package com.space.core.data.di

import com.space.core.data.local.datasource.FavoritesLocalDataSource
import com.space.core.data.local.datasource.FavoritesLocalDataSourceImpl
import com.space.core.data.mapper.ToDomainMapper
import com.space.core.data.mapper.ToEntityMapper
import com.space.core.data.repository.FavoritesRepositoryImpl
import com.space.core.domain.repository.FavoritesRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val coreDataModule = module {
    single<FavoritesLocalDataSourceImpl>() bind FavoritesLocalDataSource::class
    single<FavoritesRepositoryImpl>() bind FavoritesRepository::class
    single<ToDomainMapper>()
    single<ToEntityMapper>()
}