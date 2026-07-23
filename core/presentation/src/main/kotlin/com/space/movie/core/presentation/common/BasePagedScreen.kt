package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.space.movie.core.presentation.extension.NavCommands
import com.space.movie.core.presentation.extension.koinViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.parameter.ParametersDefinition
import kotlin.reflect.KClass

@Composable
fun <T : Any, UIState : UiState, UIEvent : UiEvent, VM : BaseVM<UIState, UIEvent>> BasePagedScreen(
    vmClass: KClass<out VM>,
    getPagingFlow: (VM) -> Flow<PagingData<T>>,
    parameters: ParametersDefinition? = null,
    content: @Composable (
        lazyPagingItems: LazyPagingItems<T>,
        state: UIState,
        onEvent: (UIEvent) -> Unit
    ) -> Unit
) {
    @Suppress("UNCHECKED_CAST")
    val viewModel = koinViewModel(vmClass, parameters) as VM
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyPagingItems = getPagingFlow(viewModel).collectAsLazyPagingItems()
    NavCommands(viewModel.navigationCommands)
    content(lazyPagingItems, state, viewModel::onEvent)
}