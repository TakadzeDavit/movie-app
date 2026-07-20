package com.space.movieapp.navigation.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.space.movieapp.R

sealed class Screen(val route: NavKey, @param:StringRes val title: Int, @param:DrawableRes val icon: Int) {
    data object HomeScreen : Screen(route = Home, title = R.string.home, R.drawable.icon_home)
    data object FavoritesScreen : Screen(route = Favorites, title = R.string.favorites, R.drawable.icon_favorite)
}

data object Home : NavKey
data object Favorites : NavKey
data class Details(val movieId: Int) : NavKey