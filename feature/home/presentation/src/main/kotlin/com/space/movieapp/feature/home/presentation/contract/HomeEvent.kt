package com.space.movieapp.feature.home.presentation.contract

import com.space.movie.core.presentation.common.UiEvent

sealed interface HomeEvent : UiEvent {
    data object ResetSearch : HomeEvent
    data object OnFilterIconClick : HomeEvent
    data class OnFilterClick(val genreId: Int) : HomeEvent
    data class OnFavoriteClick(val movieId: Int): HomeEvent
    data class OnSearchQueryChange(val text: String) : HomeEvent
}