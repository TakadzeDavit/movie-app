package com.space.movieapp.feature.catalogue.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.feature.catalogue.presentation.screen.CatalogueScreen

fun NavGraphBuilder.catalogueNavGraph() {
    composable<Route.Catalogue> {
        CatalogueScreen()
    }
}