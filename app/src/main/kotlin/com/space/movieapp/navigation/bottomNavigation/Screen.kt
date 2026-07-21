package com.space.movieapp.navigation.bottomNavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import com.space.feature.favorites.api.FavoritesFeatureKey
import com.space.feature.home.api.HomeFeatureKey
import com.space.movieapp.R

sealed class Screen(val route: NavKey, @param:StringRes val title: Int, @param:DrawableRes val icon: Int) {
    data object HomeScreen : Screen(route = HomeFeatureKey, title = R.string.home, R.drawable.icon_home)
    data object FavoritesScreen : Screen(route = FavoritesFeatureKey, title = R.string.favorites, R.drawable.icon_favorite)
}