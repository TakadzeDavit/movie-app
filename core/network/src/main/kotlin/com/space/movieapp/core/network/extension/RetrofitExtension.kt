package com.space.movieapp.core.network.extension

import com.space.common.api_result.NetworkError
import retrofit2.Response

fun <T> Response<T>.toNetworkError(): NetworkError {
    return when (this.code()) {
        401 -> NetworkError.UNAUTHORIZED
        404 -> NetworkError.NOT_FOUND
        500 -> NetworkError.SERVER_ERROR
        else -> NetworkError.UNKNOWN
    }
}