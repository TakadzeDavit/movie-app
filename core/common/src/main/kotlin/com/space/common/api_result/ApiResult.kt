package com.space.common.api_result

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(
        val errorType: NetworkError,
        val message: String? = null
    ) : ApiResult<Nothing>()

    data object Loading : ApiResult<Nothing>()
}