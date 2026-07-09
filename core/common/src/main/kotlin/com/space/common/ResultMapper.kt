package com.space.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun <Dto, Domain> Flow<ApiResult<Dto>>.mapApiResult(
    onSuccess: (Dto) -> Domain,
): Flow<ApiResult<Domain>> {
    return this.map { result ->
        when (result) {
            is ApiResult.Error -> ApiResult.Error(message = result.message)
            is ApiResult.Loading -> ApiResult.Loading
            is ApiResult.Success -> ApiResult.Success(data = onSuccess(result.data))
        }
    }
}