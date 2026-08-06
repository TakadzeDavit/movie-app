package com.space.common.exception

import com.space.common.api_result.NetworkError

class PagingException(
    val errorType: NetworkError,
    override val message: String? = null
) : Exception(message)