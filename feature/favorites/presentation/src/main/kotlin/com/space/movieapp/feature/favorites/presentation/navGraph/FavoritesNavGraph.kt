package com.space.movieapp.feature.favorites.presentation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.feature.favorites.presentation.screen.FavoritesScreen

fun NavGraphBuilder.favoritesNavGraph() {
    composable<Route.Favorites> {
        FavoritesScreen()
    }
}