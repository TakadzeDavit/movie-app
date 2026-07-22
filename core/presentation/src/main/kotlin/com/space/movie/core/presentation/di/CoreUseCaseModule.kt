package com.space.movie.core.presentation.di

import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetAllFavoritesUseCase
import com.space.core.domain.usecase.GetFavoriteIdsUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.core.domain.usecase.IsFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreUseCaseModule = module {
    singleOf(::GetAllFavoritesUseCase)
    singleOf(::DeleteByIdUseCase)
    singleOf(::GetFavoriteIdsUseCase)
    singleOf(::InsertFavoriteUseCase)
    singleOf(::IsFavoriteUseCase)
}