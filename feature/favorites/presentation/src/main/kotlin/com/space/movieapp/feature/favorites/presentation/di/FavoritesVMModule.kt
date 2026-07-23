package com.space.movieapp.feature.favorites.presentation.di

import com.space.movieapp.feature.favorites.presentation.vm.FavoritesVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesVMModule = module {
    viewModelOf(::FavoritesVM)
}