package com.space.movieapp.navigation.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.space.movieapp.core.navigation.Route
import com.space.movieapp.R

sealed class Screen(val route: Route, @param:StringRes val title: Int, @param:DrawableRes val icon: Int) {
    data object Home : Screen(route = Route.Home, title = R.string.home, R.drawable.icon_home)
    data object Favorites : Screen(route = Route.Favorites, title = R.string.favorites, R.drawable.icon_favorite)
}