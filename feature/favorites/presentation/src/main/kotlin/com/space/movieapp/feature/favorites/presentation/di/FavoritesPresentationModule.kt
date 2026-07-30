package com.space.movieapp.feature.favorites.presentation.di

import com.space.movieapp.feature.favorites.presentation.vm.FavoritesVM
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val favoritesPresentationModule = module {
    viewModel<FavoritesVM>()
}