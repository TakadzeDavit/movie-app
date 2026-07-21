package com.space.movieapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.space.feature.details.presentation.navigator.detailsEntry
import com.space.movieapp.core.navigation.LocalGlobalNavigator
import com.space.movieapp.core.navigation.featurePopTransitionSpec
import com.space.movieapp.core.navigation.featurePredictivePopTransitionSpec
import com.space.movieapp.core.navigation.featureTransitionSpec
import com.space.movieapp.core.navigation.rememberNavigator
import com.space.movieapp.feature.favorites.presentation.navigator.favoritesEntry
import com.space.movieapp.feature.home.presentation.navigator.homeEntry
import com.space.movieapp.navigation.bottomNavigation.MovieBottomBar
import com.space.movieapp.ui.MainActivity

@Composable
fun MainActivity.MovieAppContainer(
    startDestination: NavKey,
    isOnline: Boolean
) {
    val navigator = rememberNavigator(startDestination)

    CompositionLocalProvider(LocalGlobalNavigator provides navigator) {
        val currentRoute = navigator.backStack.lastOrNull()

        Scaffold(
            bottomBar = {
                if (currentRoute != null && isOnline) {
                    MovieBottomBar(
                        currentRoute = currentRoute,
                        onNavigate = { targetRoute ->
                            while (navigator.backStack.size > 1) {
                                navigator.pop()
                            }

                            if (startDestination != targetRoute) {
                                navigator.push(targetRoute)
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            NavDisplay(
                modifier = Modifier.padding(paddingValues),
                backStack = navigator.backStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                onBack = {
                    if (navigator.backStack.size > 1) navigator.pop() else finishAffinity()
                } ,
                transitionSpec = featureTransitionSpec(),
                popTransitionSpec = featurePopTransitionSpec(),
                predictivePopTransitionSpec = featurePredictivePopTransitionSpec(),
                entryProvider = entryProvider {
                    homeEntry()
                    detailsEntry()
                    favoritesEntry()
                },
            )
        }
    }
}