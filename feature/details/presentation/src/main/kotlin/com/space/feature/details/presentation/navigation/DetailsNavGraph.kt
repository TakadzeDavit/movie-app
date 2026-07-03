@file:OptIn(InternalSerializationApi::class)
package com.space.feature.details.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.space.feature.details.presentation.screen.DetailsScreen
import com.space.movieapp.core.navigation.Route
import kotlinx.serialization.InternalSerializationApi

fun NavGraphBuilder.detailsNavGraph() {
    composable<Route.Details> {
        DetailsScreen()
    }
}