package com.space.movieapp.feature.home.presentation.contract

import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI

data class HomeState (
    val screenDataState: DataState<List<PopularMovieUI>> = DataState.Loading,
) : UiState