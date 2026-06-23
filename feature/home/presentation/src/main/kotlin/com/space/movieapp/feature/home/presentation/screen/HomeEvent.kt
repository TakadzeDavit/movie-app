package com.space.movieapp.feature.home.presentation.screen

import com.space.movie.core.presentation.base.UIEvent

sealed class HomeEvent : UIEvent {
    data object FetchMovies
    data class OnMovieClick(val movieId: Int)
}