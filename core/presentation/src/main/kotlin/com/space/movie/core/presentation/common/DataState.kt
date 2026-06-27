package com.space.movie.core.presentation.common

import com.space.common.api_result.NetworkError

sealed interface DataState<out T> {
    object Loading : DataState<Nothing>
    data class Success<out T>(val data: T) : DataState<T>
    data class Error(
        val errorType: NetworkError,
        val message: String? = null
    ) : DataState<Nothing>
}