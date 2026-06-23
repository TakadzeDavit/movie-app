package com.space.movieapp.feature.home.presentation.screen

import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

data class HomeState (
    val isLoading: Boolean = false,
    val movies: List<PopularMovieUI> = emptyList(),
    val errorMessage: String? = null
)