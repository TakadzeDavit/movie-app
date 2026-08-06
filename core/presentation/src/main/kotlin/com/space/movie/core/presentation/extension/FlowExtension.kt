package com.space.movie.core.presentation.extension

import com.space.common.api_result.ApiResult
import com.space.common.api_result.NetworkError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

suspend fun <T : Any> Flow<ApiResult<T>>.handleApiResult(
    onSuccess: (T) -> Unit,
    onError: (NetworkError, String?) -> Unit = { _, _ -> },
    onLoading: () -> Unit = {},
) {
    collectLatest { result ->
        when (result) {
            is ApiResult.Success -> onSuccess(result.data)
            is ApiResult.Error -> onError(result.errorType, result.message)
            is ApiResult.Loading -> onLoading()
        }
    }
}