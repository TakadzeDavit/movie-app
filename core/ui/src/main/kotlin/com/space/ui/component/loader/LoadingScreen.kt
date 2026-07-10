package com.space.ui.component.loader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.space.ui.theme.MovieTheme

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        MovieappCircularLoader(
            mainColor = MovieTheme.colors.primary,
            backgroundColor = MovieTheme.colors.background
        )
    }
}