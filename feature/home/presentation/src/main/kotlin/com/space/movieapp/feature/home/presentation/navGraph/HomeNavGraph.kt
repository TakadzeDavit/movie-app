package com.space.movieapp.feature.home.presentation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.feature.home.presentation.screen.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    onNavigateToDetails: (Int) -> Unit
) {
    composable<Route.Home> {
        HomeScreen(
            onNavigateDetails = onNavigateToDetails
        )
    }
}