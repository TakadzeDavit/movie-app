package com.space.movie.core.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.movieapp.core.navigation.FeatureNavigationHelper
import com.space.movieapp.core.navigation.NavigationCommand
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface UiEvent
interface UiState

abstract class BaseVM<State: UiState, Event : UiEvent>(
    initialState: State
) : ViewModel(), KoinComponent {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val globalLoader by inject<GlobalLoader>()

    internal val navigationCommands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = 64
    )

    abstract fun onEvent(event: Event)

    protected fun updateState(update: State.() -> State) {
        _state.update { currentState ->
            currentState.update()
        }
    }

    protected fun launchWithLoader(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                globalLoader.showLoader()
                block()
            } finally {
                globalLoader.hideLoader()
            }
        }
    }

    protected fun globalNavigator(navigation: FeatureNavigationHelper.() -> NavigationCommand) {
        navigationCommands.tryEmit(
            FeatureNavigationHelper.navigation()
        )
    }

    protected fun showLoader() {
        globalLoader.showLoader()
    }

    protected fun hideLoader() {
        globalLoader.hideLoader()
    }
}