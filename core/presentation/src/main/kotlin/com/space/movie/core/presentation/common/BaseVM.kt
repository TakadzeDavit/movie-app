package com.space.movie.core.presentation.common

import androidx.lifecycle.ViewModel
import com.space.movieapp.core.navigation.NavigationCommand
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface UiEvent
interface UiState

abstract class BaseVM<State: UiState, Event : UiEvent>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    internal val navigationCommands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = 64
    )

    fun updateState(update: State.() -> State) {
        _state.update { currentState ->
            currentState.update()
        }
    }

    abstract fun onEvent(event: Event)
}