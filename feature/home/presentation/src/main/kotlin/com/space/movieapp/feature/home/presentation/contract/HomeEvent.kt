package com.space.movieapp.feature.home.presentation.contract

import com.space.movie.core.presentation.common.UiEvent

sealed interface HomeEvent : UiEvent {
    data object GetPopularMovies : HomeEvent
    data class OnFavoriteClick(val movieId: Int): HomeEvent
}