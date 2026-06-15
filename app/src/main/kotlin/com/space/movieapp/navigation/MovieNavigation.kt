package com.space.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.space.common.navigation.Route
import com.space.movieapp.feature.catalogue.presentation.navigation.catalogueNavGraph

@Composable
fun MovieNavigation(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        modifier = modifier,
        startDestination = Route.Catalogue,
        navController = navController
    ) {
        catalogueNavGraph()
    }
}