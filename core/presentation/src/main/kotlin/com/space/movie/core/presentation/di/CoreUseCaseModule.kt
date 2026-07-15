package com.space.movie.core.presentation.di

import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetAllFavoritesUseCase
import com.space.core.domain.usecase.GetFavoriteIdsUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.core.domain.usecase.IsFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreUseCaseModule = module {
    factoryOf(::GetAllFavoritesUseCase)
    factoryOf(::DeleteByIdUseCase)
    factoryOf(::GetFavoriteIdsUseCase)
    factoryOf(::InsertFavoriteUseCase)
    factoryOf(::IsFavoriteUseCase)
}