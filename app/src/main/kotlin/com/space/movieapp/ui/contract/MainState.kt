package com.space.movieapp.ui.contract

data class MainState (
    val isLoading: Boolean = true,
    val startDestination: Route = Route.Home,
    val isOnline: Boolean = false
)