package com.space.feature.details.presentation.contract

import com.space.feature.details.domain.model.MovieDetails
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState

data class DetailsState (
     val loading: Boolean = false,
     val movieState: DataState<MovieDetails> = DataState.Loading
) : UiState