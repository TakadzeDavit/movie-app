package com.space.movieapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun AutoHideBottomBar() {
    val bottomBarState = requireBottomBarState()

    DisposableEffect(Unit) {
        bottomBarState.targetState = false
        onDispose {
            bottomBarState.targetState = true
        }
    }
}