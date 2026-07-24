package com.space.movie.core.presentation.extension

import com.space.common.api_result.NetworkError
import com.space.movie.core.presentation.R
import com.space.movie.core.presentation.model.ErrorUiModel

fun NetworkError.toUiModel(): ErrorUiModel = when (this) {
    NetworkError.NO_INTERNET -> ErrorUiModel(
        titleResId = R.string.data_can_t_be_loaded,
        descriptionResId = R.string.internet_connection_or_some_other_server_error
    )

    NetworkError.UNAUTHORIZED -> ErrorUiModel(
        titleResId = R.string.error_unauthorized_title,
        descriptionResId = R.string.error_unauthorized_desc
    )

    NetworkError.NOT_FOUND -> ErrorUiModel(
        titleResId = R.string.error_not_found_title,
        descriptionResId = R.string.error_not_found_desc
    )

    NetworkError.SERVER_ERROR -> ErrorUiModel(
        titleResId = R.string.error_server_error_title,
        descriptionResId = R.string.error_server_error_desc
    )

    NetworkError.EMPTY_RESPONSE -> ErrorUiModel(
        titleResId = R.string.error_empty_response_title,
        descriptionResId = R.string.error_empty_response_desc
    )

    NetworkError.UNKNOWN -> ErrorUiModel(
        titleResId = R.string.error_unknown_title,
        descriptionResId = R.string.error_unknown_desc
    )
}