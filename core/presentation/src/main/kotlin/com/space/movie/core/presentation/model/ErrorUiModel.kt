package com.space.movie.core.presentation.model

import androidx.annotation.StringRes

data class ErrorUiModel(
    @param:StringRes
    val titleResId: Int,
    @param:StringRes
    val descriptionResId: Int
)