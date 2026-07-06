package com.space.movieapp.feature.favorites.presentation.screen

import com.space.feature.favorites.domain.model.FavoriteMovie
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState

data class FavoritesState (
    val favoriteMovies: DataState<List<FavoriteMovie>> = DataState.Loading
) : UiState