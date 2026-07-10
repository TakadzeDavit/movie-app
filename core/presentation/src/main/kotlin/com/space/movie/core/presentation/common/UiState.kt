package com.space.movie.core.presentation.common

sealed interface DataState<out T> {
    object Loading : DataState<Nothing>
    data class Success<out T>(val data: T) : DataState<T>
    data class Error(val message: String) : DataState<Nothing>
}