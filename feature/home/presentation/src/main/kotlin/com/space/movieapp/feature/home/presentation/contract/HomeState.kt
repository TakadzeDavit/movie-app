package com.space.movieapp.feature.home.presentation.contract

import androidx.compose.foundation.pager.PagerState
import androidx.paging.PagingData
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState
import com.space.movie.feature.home.domain.model.Genre
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState(
    val filters: List<Genre> = emptyList(),
    val searchQuery: String = "",
    val genresLoaded: Boolean = false,
    val selectedGenreId: Int? = null,
    val areFiltersExpanded: Boolean = false,
    val isOnline: Boolean = true
) : UiState