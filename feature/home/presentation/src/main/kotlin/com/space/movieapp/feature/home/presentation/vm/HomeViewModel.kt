package com.space.movieapp.feature.home.presentation.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.common.network.NetworkObserver
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movie.feature.home.domain.usecase.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.SearchMoviesUseCase
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val popularMovieUiMapper: PopularMovieUiMapper,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val networkObserver: NetworkObserver
) : BaseViewModel<HomeState, HomeEvent, EmptySideEffect>(HomeState()) {

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val moviesPagedFlow = state
        .filter { it.genresLoaded }
        .map { Pair(it.searchQuery, it.selectedGenreId) }
        .distinctUntilChanged()
        .debounce(300L.milliseconds)
        .flatMapLatest { (query, _) ->
            when {
                query.isNotBlank() -> searchMoviesUseCase(query)
                else -> getPopularMoviesUseCase()
            }.map { pagingData ->
                pagingData.map(popularMovieUiMapper::map)
            }
        }
        .cachedIn(viewModelScope)

    init {
        getGenres()
        observeNetwork()
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClick -> toggleFavorite(event.movieId)
            is HomeEvent.OnSearchQueryChange -> updateState { copy(searchQuery = event.text) }
            is HomeEvent.OnFilterClick -> updateState { copy(areFiltersExpanded = !areFiltersExpanded) }
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.isConnected.collect { connected ->
                updateState { copy(isOnline = connected) }
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            getGenresUseCase.invoke().handleApiResult(
                onSuccess = { genres ->
                    updateState {
                        copy(
                            filters = genres,
                            genresLoaded = true
                        )
                    }
                },
                onError = { _, _ ->
                    updateState { copy(genresLoaded = true) }
                }
            )
        }
    }

    private fun toggleFavorite(movieId: Int) {
    }
}