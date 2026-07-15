package com.space.movieapp.feature.home.presentation.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.common.network.NetworkObserver
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMovieUiMapper: PopularMovieUiMapper,
    private val networkObserver: NetworkObserver
) : BaseViewModel<HomeState, HomeEvent, EmptySideEffect>(HomeState()) {

    init {
        getPopularMovies()
        observeNetwork()
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetPopularMovies -> getPopularMovies()
            is HomeEvent.OnFavoriteClick -> toggleFavorite(event.movieId)
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.isConnected.collect { connected ->
                updateState { copy(isOnline = connected) }
            }
        }
    }

    private fun getPopularMovies() {
        val pagedMoviesFlow = getPopularMoviesUseCase.invoke()
            .map { pagingData ->
                pagingData.map(popularMovieUiMapper::map)
            }
            .cachedIn(viewModelScope)

        updateState { copy(moviesPagedData = pagedMoviesFlow) }
    }

    private fun toggleFavorite(movieId: Int) {
    }
}