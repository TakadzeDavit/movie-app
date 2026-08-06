package com.space.feature.details.presentation.contract

import com.space.movie.core.presentation.common.UiEvent

sealed interface DetailsEvent : UiEvent {
    data object OnFavoriteClick : DetailsEvent
    data object OnRefreshClick : DetailsEvent
    data object OnBackClick : DetailsEvent
}