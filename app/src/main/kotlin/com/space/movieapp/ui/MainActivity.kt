package com.space.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.movie.core.presentation.common.GlobalLoader
import com.space.movieapp.navigation.MovieAppContainer
import com.space.movieapp.ui.vm.MainActivityVM
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.state.value.isLoading
        }

        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            MovieAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    MovieAppContainer(
                        startDestination = state.startDestination,
                        isOnline = state.isOnline
                    )

                    FullScreenLoader(modifier = Modifier.zIndex(1f))
                }
            }
        }
    }
}

@Composable
private fun FullScreenLoader(
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