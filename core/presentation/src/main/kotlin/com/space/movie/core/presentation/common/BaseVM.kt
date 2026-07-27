package com.space.movie.core.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.movie.core.presentation.extension.inject
import com.space.movieapp.core.navigation.NavigationCommand
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

interface UiEvent
interface UiState

abstract class BaseVM<State: UiState, Event : UiEvent>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    internal val globalLoader by inject<GlobalLoader>()

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
}