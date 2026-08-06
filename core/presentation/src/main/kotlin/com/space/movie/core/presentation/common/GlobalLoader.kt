package com.space.movie.core.presentation.common

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface GlobalLoader {

    val isLoading: StateFlow<Boolean>

    fun showLoader()

    fun hideLoader()
}