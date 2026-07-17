package com.space.movieapp.ui.contract

import com.space.movieapp.core.navigation.Route

data class MainState (
    val isLoading: Boolean = true,
    val startDestination: Route = Route.Home,
    val isOnline: Boolean = false
)