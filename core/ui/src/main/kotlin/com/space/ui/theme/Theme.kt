package com.space.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun MovieAppTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMovieColors provides DarkMovieColors,
        LocalMovieTypography provides MovieTypography
    ) {
        MaterialTheme(
            colorScheme = darkColorScheme(
                primary = DarkMovieColors.primary,
                background = DarkMovieColors.background,
                surface = DarkMovieColors.surface
            ),
            content = content
        )
    }
}

object MovieTheme {
    val colors: MovieAppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMovieColors.current

    val typography: MovieAppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMovieTypography.current
}