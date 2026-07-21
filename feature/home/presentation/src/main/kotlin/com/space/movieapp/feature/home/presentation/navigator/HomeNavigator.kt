package com.space.movieapp.feature.home.presentation.navigator

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.space.feature.home.api.HomeFeatureKey
import com.space.movieapp.feature.home.presentation.screen.HomeScreen

fun EntryProviderScope<NavKey>.homeEntry() {
    entry<HomeFeatureKey> {
        HomeScreen()
    }
}