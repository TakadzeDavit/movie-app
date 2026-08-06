package com.space.movieapp.feature.favorites.presentation.contract

import androidx.annotation.StringRes
import com.space.core.domain.model.PopularMovie
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState

data class FavoritesState (
    val favoriteMovies: DataState<List<PopularMovie>> = DataState.Loading
) : UiState