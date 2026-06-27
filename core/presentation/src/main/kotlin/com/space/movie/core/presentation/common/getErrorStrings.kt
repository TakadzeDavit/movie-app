package com.space.movie.core.presentation.common

import com.space.common.api_result.NetworkError
import com.space.movie.core.presentation.R
import com.space.movie.core.presentation.model.ErrorUiModel

fun getErrorStrings(errorType: NetworkError): ErrorUiModel {
    return when (errorType) {
        NetworkError.NO_INTERNET -> {
            ErrorUiModel(
                R.string.data_can_t_be_loaded,
                R.string.internet_connection_or_some_other_server_error
            )
        }

        NetworkError.UNAUTHORIZED -> {
            ErrorUiModel(
                title = R.string.error_unauthorized_title,
                description = R.string.error_unauthorized_desc
            )
        }

        NetworkError.NOT_FOUND -> {
            ErrorUiModel(
                title = R.string.error_not_found_title,
                description = R.string.error_not_found_desc
            )
        }

        NetworkError.SERVER_ERROR -> {
            ErrorUiModel(
                title = R.string.error_server_error_title,
                description = R.string.error_server_error_desc
            )
        }

        NetworkError.EMPTY_RESPONSE -> {
            ErrorUiModel(
                title = R.string.error_empty_response_title,
                description = R.string.error_empty_response_desc
            )
        }

        NetworkError.UNKNOWN -> {
            ErrorUiModel(
                title = R.string.error_unknown_title,
                description = R.string.error_unknown_desc
            )
        }
    }
}