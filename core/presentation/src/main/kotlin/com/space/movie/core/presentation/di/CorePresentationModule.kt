package com.space.movie.core.presentation.di

import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetAllFavoritesUseCase
import com.space.core.domain.usecase.GetFavoriteIdsUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.core.domain.usecase.IsFavoriteUseCase
import com.space.movie.core.presentation.common.GlobalLoader
import com.space.movie.core.presentation.common.GlobalLoaderImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val corePresentationModule = module {
    single<GetAllFavoritesUseCase>()
    single<DeleteByIdUseCase>()
    single<GetFavoriteIdsUseCase>()
    single<InsertFavoriteUseCase>()
    single<IsFavoriteUseCase>()
    single<GlobalLoaderImpl>() bind GlobalLoader::class
}