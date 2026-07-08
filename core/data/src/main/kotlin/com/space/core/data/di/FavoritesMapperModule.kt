package com.space.core.data.di

import com.space.core.data.mapper.ToDomainMapper
import com.space.core.data.mapper.ToEntityMapper
import org.koin.dsl.module

val favoritesMapperModule = module {
    factory { ToDomainMapper() }
    factory { ToEntityMapper() }
}