package com.space.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.space.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.MainActivityViewModel
import com.space.ui.theme.MovieAppTheme
import com.space.common.navigation.Route

class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.loading.value
        }

        enableEdgeToEdge()
        setContent {
            val isLoading by viewModel.loading.collectAsStateWithLifecycle()
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

            MovieAppTheme {
                if (!isLoading) {
                    MainScreen(startDestination = startDestination)
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    startDestination: Route
) {
    val navController = rememberNavController()


    Scaffold() { paddingValues ->

        MovieNavigation(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}