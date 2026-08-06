package com.space.feature.details.presentation.contract

import com.space.feature.details.domain.model.MovieDetails
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState

data class DetailsState (
     val movieState: DataState<MovieDetails> = DataState.Loading,
     val isFavorite: Boolean = false
) : UiState