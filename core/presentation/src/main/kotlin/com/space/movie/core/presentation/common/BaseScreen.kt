package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.scope.KoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

/**
 * A generic base Composable screen that handles Koin Scope management, ViewModel injection,
 * UI State collection, and navigation event listening.
 *
 * This component encapsulates the boilerplate code required for setting up a screen driven by a
 * MVI/MVVM pattern with Koin Dependency Injection.
 *
 * @param UIState The type representing the UI state of the screen, implementing [UiState].
 * @param UIEvent The type representing user interactions or events, implementing [UiEvent].
 * @param vmClass The [VmClass] reference used by Koin to resolve the appropriate ViewModel.
 * @param scopeQualifier The Koin [Qualifier] defining the lifecycle scope for this screen.
 * @param parameters Optional parameters to pass into the ViewModel constructor via Koin.
 * @param content The Composable content slot to render the UI, providing the current [UIState]
 * and an `onEvent` callback to dispatch [UIEvent]s to the ViewModel.
 */

@OptIn(KoinExperimentalAPI::class)
@Composable
fun <UIState : UiState, UIEvent : UiEvent> BaseScreen(
    vmClass: VmClass<UIState, UIEvent>,
    scopeQualifier: Qualifier,
    parameters: ParametersDefinition? = null,
    content: @Composable (state: UIState, onEvent: (UIEvent) -> Unit) -> Unit
) {
    KoinScope(
        scopeDefinition = { getOrCreateScope(scopeQualifier.value, scopeQualifier) }
    ) {
        val viewModel = koinViewModel(
            vmClass = vmClass,
            parameters = parameters
        )
        val state by viewModel.state.collectAsStateWithLifecycle()
        NavCommands(viewModel.navigationCommands)
        content(state, viewModel::onEvent)
    }
}