package com.space.movieapp.core.network.apicall

import com.space.common.api_result.ApiResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ResponseHandler {
    fun <T> apiCall(apiCall: suspend () -> Response<T>): Flow<ApiResult<T>>
}