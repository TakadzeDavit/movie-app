package com.space.movieapp.feature.home.presentation.screen

import com.space.movie.core.presentation.base.UISideEffect

class HomeSideEffect : UISideEffect {
    data class NavigateToDetails(val movieId: Int)
    data class ShowSnackBar(val message: String)
}