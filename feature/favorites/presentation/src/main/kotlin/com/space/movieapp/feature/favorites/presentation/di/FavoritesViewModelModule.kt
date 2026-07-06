package com.space.movieapp.feature.favorites.presentation.di

import com.space.movieapp.feature.favorites.presentation.vm.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoritesViewModelModule = module {
    viewModelOf(::FavoritesViewModel)
}