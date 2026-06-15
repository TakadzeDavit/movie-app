package com.space.movieapp.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Catalogue : Route
}