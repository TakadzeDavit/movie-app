package com.space.movieapp.core.network.apicall

import com.space.common.api_result.ApiResult
import com.space.common.api_result.NetworkError
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import kotlinx.coroutines.flow.Flow

class ResponseHandlerImpl : ResponseHandler {
    override fun <T> apiCall(
        apiCall: suspend () -> Response<T>
    ): Flow<ApiResult<T>> = flow {
        emit(ApiResult.Loading)
        try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(ApiResult.Success(body))
                } else {
                    emit(ApiResult.Error(NetworkError.EMPTY_RESPONSE))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorType = when (response.code()) {
                    401 -> NetworkError.UNAUTHORIZED
                    404 -> NetworkError.NOT_FOUND
                    500 -> NetworkError.SERVER_ERROR
                    else -> NetworkError.UNKNOWN
                }
                emit(ApiResult.Error(errorType, message = errorBody))
            }
        } catch (e: Exception) {
            val errorType = when (e) {
                is IOException -> NetworkError.NO_INTERNET
                else -> NetworkError.UNKNOWN
            }
            emit(ApiResult.Error(errorType, message = e.localizedMessage))
        }
    }
}
