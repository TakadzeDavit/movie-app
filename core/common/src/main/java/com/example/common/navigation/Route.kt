package com.example.common.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Catalogue : Route
}