package com.space.movieapp.feature.home.presentation.contract

import com.space.movie.core.presentation.common.UiState
import com.space.core.domain.model.Genre

data class HomeState(
    val filters: List<Genre> = emptyList(),
    val searchQuery: String = "",
    val genresLoaded: Boolean = false,
    val selectedGenreId: Int? = null,
    val areFiltersExpanded: Boolean = false,
    val showFilterNameOnCard: String? = null,
    val isOnline: Boolean = true
) : UiState