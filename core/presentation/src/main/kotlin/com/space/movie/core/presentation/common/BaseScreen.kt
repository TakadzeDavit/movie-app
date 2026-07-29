package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.core.parameter.ParametersDefinition

@Composable
fun <UIState : UiState, UIEvent : UiEvent> BaseScreen(
    vmClass: VmClass<UIState, UIEvent>,
    parameters: ParametersDefinition? = null,
    content: @Composable (state: UIState, onEvent: (UIEvent) -> Unit) -> Unit
) {
    val viewModel = koinViewModel(vmClass, parameters)
    val state by viewModel.state.collectAsStateWithLifecycle()
    NavCommands(viewModel.navigationCommands)
    content(state, viewModel::onEvent)
}