package com.space.movieapp.feature.favorites.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.feature.favorites.api.FavoritesFeatureKey
import com.space.movieapp.feature.favorites.presentation.screen.FavoritesScreen

fun EntryProviderScope<NavKey>.favoritesEntry() {
    entry<FavoritesFeatureKey> {
        FavoritesScreen()
    }
}