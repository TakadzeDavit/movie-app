package com.space.ui.component.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.MovieTheme.colors

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

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    MovieAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            LoadingScreen()
        }
    }
}