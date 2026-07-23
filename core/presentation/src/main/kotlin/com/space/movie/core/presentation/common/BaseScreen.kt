package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.movie.core.presentation.extension.NavCommands
import com.space.movie.core.presentation.extension.VmClass
import com.space.movie.core.presentation.extension.koinViewModel
import org.koin.core.parameter.ParametersDefinition

@Composable
fun <UIState : UiState, UIEvent : UiEvent> BaseScreen(
    vmClass: VmClass<UIState, UIEvent>,
    parameters: ParametersDefinition? = null,
    content: @Composable (state: UIState, onEvent: (UIEvent) -> Unit) -> Unit
) {
    val viewModel = koinViewModel(vmClass, parameters)
    val state = viewModel.state.collectAsStateWithLifecycle().value
    NavCommands(viewModel.navigationCommands)
    content(state, viewModel::onEvent)
}