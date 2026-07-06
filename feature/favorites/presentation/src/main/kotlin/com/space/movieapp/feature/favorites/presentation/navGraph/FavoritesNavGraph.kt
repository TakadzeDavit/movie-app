package com.space.movieapp.feature.favorites.presentation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.feature.favorites.presentation.screen.FavoritesScreen
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
fun NavGraphBuilder.favoritesNavGraph(
    navigateOnDetails: (Int) -> Unit
) {
    composable<Route.Favorites> {
        FavoritesScreen(
            onNavigateDetails = { navigateOnDetails(it) }
        )
    }
}