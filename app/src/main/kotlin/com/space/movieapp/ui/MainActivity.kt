package com.space.movieapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.space.movieapp.navigation.MovieAppContainer
import com.space.movieapp.navigation.bottomNavigation.MovieBottomBar
import com.space.movieapp.navigation.navhost.MovieNavigation
import com.space.movieapp.ui.vm.MainVM
import com.space.ui.theme.MovieAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainVM by viewModel()

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
                if (!state.isLoading) {
                    MovieAppContainer(
                        startDestination = state.startDestination,
                        isOnline = state.isOnline
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    startDestination: Route,
    isOnline: Boolean
) {

}