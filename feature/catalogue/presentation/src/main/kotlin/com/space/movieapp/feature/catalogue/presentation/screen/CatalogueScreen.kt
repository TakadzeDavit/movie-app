package com.space.movieapp.feature.catalogue.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ui.theme.MovieTheme

@Composable
fun CatalogueScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieTheme.colors.background)
    ) { }
}