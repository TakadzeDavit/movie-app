package com.example.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val YellowPrimary = Color(0xFFFFC44A)
val Neutral01Black = Color(0xFF080808)
val Neutral02DarkestGrey = Color(0xFF1C1C1C)
val Neutral03DarkGrey = Color(0xFF5D5D5D)
val Neutral04Grey = Color(0xFF808080)
val Neutral05LightGrey = Color(0xFFA5A5A5)
val Neutral06LighterGrey = Color(0xFFCACACA)
val Neutral07LightestGrey = Color(0xFFDEDEDE)
val Neutral08Whisper = Color(0xFFEAEAEA)

data class MovieAppColors(
    val primary: Color,
    val background: Color,
    val surface: Color,
    val onPrimary: Color,
    val onBackground: Color,
    val onSurface: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textHint: Color,
    val border: Color
)

val DarkMovieColors = MovieAppColors(
    primary = YellowPrimary,
    background = Neutral01Black,
    surface = Neutral02DarkestGrey,
    onPrimary = Neutral01Black,
    onBackground = Neutral08Whisper,
    onSurface = Neutral07LightestGrey,
    textSecondary = Neutral06LighterGrey,
    textTertiary = Neutral04Grey,
    textHint = Neutral05LightGrey,
    border = Neutral03DarkGrey
)

val LocalMovieColors = staticCompositionLocalOf<MovieAppColors> {
    error("No MovieAppColors provided")
}