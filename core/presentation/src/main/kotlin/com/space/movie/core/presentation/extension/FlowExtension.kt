package com.space.movie.core.presentation.extension

import com.space.common.api_result.ApiResult
import kotlinx.coroutines.flow.Flow

suspend fun <T : Any> Flow<ApiResult<T>>.handleApiResult(
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit = {},
    onLoading: () -> Unit = {},
) {
    collect { result ->
        when (result) {
            is ApiResult.Success -> onSuccess(result.data)
            is ApiResult.Error -> onError(result.message)
            is ApiResult.Loading -> onLoading()
        }
    }
}