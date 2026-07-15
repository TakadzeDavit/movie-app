package com.space.movieapp.feature.favorites.presentation.di

import com.space.feature.favorites.domain.usecase.DeleteByIdUseCase
import com.space.feature.favorites.domain.usecase.GetAllFavoritesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val favoritesUseCaseModule = module {
    factoryOf(::GetAllFavoritesUseCase)
    factoryOf(::DeleteByIdUseCase)
}