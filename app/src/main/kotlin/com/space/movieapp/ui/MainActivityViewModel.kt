package com.space.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.common.network.NetworkObserver
import com.space.movieapp.core.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MainActivityViewModel(
    private val networkObserver: NetworkObserver
) : ViewModel() {
    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val loading = _isLoading.asStateFlow()

    private val _startDestination: MutableStateFlow<Route> = MutableStateFlow(Route.Home)
    val startDestination = _startDestination.asStateFlow()

    private val _isOnline: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isOnline = _isOnline.asStateFlow()

    init {
        observeNetwork()

        viewModelScope.launch {
            // 1. get token
            // 2. if token is blank, set startDestination to Route.Auth, otherwise to Route.Catalogue

            delay(1000.milliseconds)

            _isLoading.update { false }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.isConnected.collectLatest { connected ->
                _isOnline.update { connected }
            }
        }
    }
}