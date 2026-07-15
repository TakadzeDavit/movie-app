package com.space.movieapp.feature.home.presentation.contract

import androidx.paging.PagingData
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.UiState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeState (
    val moviesPagedData: Flow<PagingData<PopularMovieUI>> = flowOf(PagingData.empty()),
    val isOnline: Boolean = true
) : UiState