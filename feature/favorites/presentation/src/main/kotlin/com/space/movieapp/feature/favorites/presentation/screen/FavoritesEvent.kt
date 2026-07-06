package com.space.movieapp.feature.favorites.presentation.screen

import com.space.movie.core.presentation.common.UiEvent

sealed interface FavoritesEvent : UiEvent {
    data class RemoveFromFavorites(val movieId: Int) : FavoritesEvent
    data object OnRefreshClick : FavoritesEvent
}