package com.space.movie.core.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import org.koin.compose.scope.KoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

/**
 * A generic Base Composable screen that handles **Paging 3 data**, **Koin Scopes**,
 * and **MVI/MVVM Architecture** state management.
 *
 * It automatically binds the screen to a specific [KoinScope], collects the ViewModel's state,
 * observes navigation commands, and converts a [PagingData] Flow into [LazyPagingItems].
 *
 * @param T The type of items inside the Paging list.
 * @param UIState The UI state type extending [UiState].
 * @param UIEvent The UI event type extending [UiEvent].
 * @param VM The ViewModel type extending [BaseVM].
 *
 * @param vmClass The [KClass] of the ViewModel to be injected by Koin (e.g., `HomeVM::class`).
 * @param scopeQualifier The Koin [Qualifier] used to define the lifecycle scope (e.g., `named<HomeScope>()`).
 * @param getPagingFlow A lambda function extracting the [PagingData] Flow from the ViewModel.
 * @param parameters Optional parameters to pass to the ViewModel factory upon creation.
 * @param content The Composable content block providing [LazyPagingItems], current [UIState], and an event callback.
 *
 * @see BaseVM
 * @see KoinScope
 */

@OptIn(KoinExperimentalAPI::class)
@Composable
fun <T : Any, UIState : UiState, UIEvent : UiEvent, VM : BaseVM<UIState, UIEvent>> BasePagedScreen(
    vmClass: KClass<out VM>,
    scopeQualifier: Qualifier,
    getPagingFlow: (VM) -> Flow<PagingData<T>>,
    parameters: ParametersDefinition? = null,
    content: @Composable (
        lazyPagingItems: LazyPagingItems<T>,
        state: UIState,
        onEvent: (UIEvent) -> Unit
    ) -> Unit
) {
    KoinScope(
        scopeDefinition = { getOrCreateScope(scopeQualifier.value, scopeQualifier) }
    ) {
        @Suppress("UNCHECKED_CAST")
        val viewModel = koinViewModel(vmClass, parameters) as VM
        val state by viewModel.state.collectAsStateWithLifecycle()
        val lazyPagingItems = getPagingFlow(viewModel).collectAsLazyPagingItems()
        NavCommands(viewModel.navigationCommands)
        content(lazyPagingItems, state, viewModel::onEvent)
    }
}