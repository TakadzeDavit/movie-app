package com.space.movie.core.presentation.model

import androidx.annotation.StringRes

data class ErrorUiModel(
    @param:StringRes
    val title: Int,
    @param:StringRes
    val description: Int
)