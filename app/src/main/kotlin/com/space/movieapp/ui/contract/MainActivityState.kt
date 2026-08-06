package com.space.movieapp.ui.contract

import androidx.navigation3.runtime.NavKey
import com.space.feature.home.api.HomeFeatureKey

data class MainActivityState (
    val isLoading: Boolean = true,
    val startDestination: NavKey = HomeFeatureKey
)