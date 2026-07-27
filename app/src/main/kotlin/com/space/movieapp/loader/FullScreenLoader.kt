package com.space.movieapp.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.movie.core.presentation.common.GlobalLoader
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieTheme.colors
import org.koin.compose.koinInject

@Composable
fun FullScreenLoader(
    modifier: Modifier
) {
    val globalLoader: GlobalLoader = koinInject()
    val isLoading by globalLoader.isLoading.collectAsStateWithLifecycle()

    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(colors.background),
            contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    }
}