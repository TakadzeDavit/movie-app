package com.space.movieapp.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.common.network.NetworkObserver
import com.space.movieapp.ui.contract.MainActivityState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivityVM : ViewModel() {
    private val _state : MutableStateFlow<MainActivityState> = MutableStateFlow(MainActivityState())
    val state = _state.asStateFlow()

    init {
        loadDataAndFinishSplash()
    }

    private fun loadDataAndFinishSplash() {
        viewModelScope.launch {
            delay(3000.milliseconds)
            _state.update { it.copy(isLoading = false) }
        }
    }
}