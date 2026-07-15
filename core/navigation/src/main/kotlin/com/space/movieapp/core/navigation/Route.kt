package com.space.movieapp.core.navigation

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route
    @Serializable
    data object Favorites : Route
    @InternalSerializationApi @Serializable
    data class Details(val movieId: Int) : Route
}