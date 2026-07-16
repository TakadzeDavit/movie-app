package com.space.movieapp.feature.home.presentation.contract

import androidx.compose.foundation.text.input.TextFieldState
import androidx.paging.PagingData
import com.space.movie.core.presentation.common.UiState
import com.space.core.domain.model.Genre
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val movies: Flow<PagingData<PopularMovieUI>> = emptyFlow(),
    val filters: List<Genre> = emptyList(),
    val searchState: TextFieldState = TextFieldState(),
    val genresLoaded: Boolean = false,
    val selectedGenreId: Int? = null,
    val areFiltersExpanded: Boolean = false,
    val showFilterNameOnCard: String? = null,
    val isOnline: Boolean = true
) : UiState