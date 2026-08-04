package com.space.movieapp.core.navigation

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

val LocalGlobalNavigator = staticCompositionLocalOf<Navigator?> { null }
val LocalBottomBarVisibility = compositionLocalOf {
    MutableTransitionState(true)
}

@Composable
fun globalNavigator() = LocalGlobalNavigator.current

@Composable
fun requireGlobalNavigator(): Navigator {
    return globalNavigator() ?: throw IllegalStateException("Global navigator is not available")
}

@Composable
fun requireBottomBarState(): MutableTransitionState<Boolean> {
    return LocalBottomBarVisibility.current
}