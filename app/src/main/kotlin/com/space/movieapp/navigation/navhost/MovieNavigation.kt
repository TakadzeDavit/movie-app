@file:OptIn(InternalSerializationApi::class)
package com.space.movieapp.navigation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.space.feature.details.presentation.navigation.detailsNavGraph
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.feature.favorites.presentation.navGraph.favoritesNavGraph
import com.space.movieapp.feature.home.presentation.navGraph.homeNavGraph
import kotlinx.serialization.InternalSerializationApi

@Composable
fun MovieNavigation(
    navController: NavHostController,
    startDestination: Route,
    modifier: Modifier
) {
    NavHost(
        modifier = modifier,
        startDestination = startDestination,
        navController = navController,
        enterTransition = { fadeIn(animationSpec = tween(300)) },
        exitTransition = { fadeOut(animationSpec = tween(30)) }
    ) {
        homeNavGraph(
            onNavigateToDetails = { movieId ->
                navController.navigate(Route.Details(movieId = movieId))
            }
        )

        favoritesNavGraph(
            navigateOnDetails = { movieId ->
                navController.navigate(Route.Details(movieId = movieId))
            }
        )

        detailsNavGraph(
            onBackClick = {
                navController.navigateUp()
            }
        )
    }
}