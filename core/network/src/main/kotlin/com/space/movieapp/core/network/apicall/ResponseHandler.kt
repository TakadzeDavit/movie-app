package com.space.movieapp.core.network.apicall

import com.space.common.ApiResult
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResponseHandler {
    fun <T> apiCall(apiCall: suspend () -> Response<T>) = flow {
        emit(ApiResult.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(ApiResult.Success(body))
                } else {
                    emit(ApiResult.Error("Empty response"))
                }
            } else {
                emit(ApiResult.Error(response.errorBody()?.string().orEmpty()))
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> emit(ApiResult.Error(e.message.orEmpty()))
                is HttpException -> emit(ApiResult.Error(e.message.orEmpty()))
                else -> emit(ApiResult.Error(e.message.orEmpty()))
            }
        }
    }
}