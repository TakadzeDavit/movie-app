package com.space.common.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Catalogue : Route
}