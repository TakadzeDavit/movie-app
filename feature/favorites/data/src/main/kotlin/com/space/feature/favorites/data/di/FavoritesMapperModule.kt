package com.space.feature.favorites.data.di

import com.space.feature.favorites.data.mapper.ToDomainMapper
import org.koin.dsl.module

val favoritesMapperModule = module {
    factory { ToDomainMapper() }
}