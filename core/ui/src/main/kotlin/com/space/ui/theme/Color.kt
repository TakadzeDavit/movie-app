package com.space.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val YellowPrimary = Color(0xFFFFC44A)
private val Neutral01Black = Color(0xFF080808)
private val Neutral02DarkestGrey = Color(0xFF1C1C1C)
private val Neutral03DarkGrey = Color(0xFF5D5D5D)
private val Neutral04Grey = Color(0xFF808080)
private val Neutral05LightGrey = Color(0xFFA5A5A5)
private val Neutral06LighterGrey = Color(0xFFCACACA)
private val Neutral07LightestGrey = Color(0xFFDEDEDE)
private val Neutral08Whisper = Color(0xFFEAEAEA)

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