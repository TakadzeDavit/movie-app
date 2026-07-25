package com.space.movieapp.loader

import com.space.movie.core.presentation.common.GlobalLoader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GlobalLoaderImpl : GlobalLoader {
    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    override fun showLoader() {
        _isLoading.update { true }
    }

    override fun hideLoader() {
        _isLoading.update { false }
    }
}