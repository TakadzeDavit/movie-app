package com.space.movieapp.feature.home.presentation.vm

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.common.network.NetworkObserver
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movie.feature.home.domain.usecase.FilterMoviesUseCase
import com.space.movie.feature.home.domain.usecase.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.SearchMoviesUseCase
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val popularMovieUiMapper: PopularMovieUiMapper,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val filterMoviesUseCase: FilterMoviesUseCase,
    private val networkObserver: NetworkObserver
) : BaseViewModel<HomeState, HomeEvent, EmptySideEffect>(HomeState()) {

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClick -> Unit
            is HomeEvent.OnSearchQueryChange -> updateState { copy(searchQuery = event.text) }
            is HomeEvent.OnFilterIconClick -> updateState { copy(areFiltersExpanded = !areFiltersExpanded) }
            is HomeEvent.ResetSearch -> updateState { copy(searchQuery = "") }
            is HomeEvent.OnFilterClick -> onFilterClick(event.genreId)
        }
    }

    init {
        loadGenres()
        observeNetwork()
    }

    @OptIn(FlowPreview::class)
    private val debouncedQueryFlow = state
        .map { it.searchQuery }
        .distinctUntilChanged()
        .debounce(500.milliseconds)

    private val genreIdFlow = state
        .map { it.selectedGenreId }
        .distinctUntilChanged()

    private val genresLoadedFlow = state
        .map { it.genresLoaded }
        .distinctUntilChanged()

    @OptIn(ExperimentalCoroutinesApi::class)
    val moviesPagedFlow = combine(
        flow = genresLoadedFlow,
        flow2 = debouncedQueryFlow,
        flow3 = genreIdFlow
    ) { genresLoaded, query, genreId ->
        if (genresLoaded) query to genreId else null
    }
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { (query, genreId) -> resolveMovies(query, genreId) }
        .cachedIn(viewModelScope)

    private fun resolveMovies(query: String, genreId: Int?): Flow<PagingData<PopularMovieUI>> {
        val flow = when {
            query.isNotBlank() -> searchMoviesUseCase(query)
            genreId != null -> filterMoviesUseCase(genreId)
            else -> getPopularMoviesUseCase()
        }
        return flow.map { pagingData -> pagingData.map(popularMovieUiMapper::map) }
    }

    private fun onFilterClick(genreId: Int) {
        updateState {
            val newGenreId = if (selectedGenreId == genreId) null else genreId
            val newFilterName = if (newGenreId != null) {
                filters.firstOrNull { it.id == newGenreId }?.name
            } else null

            copy(
                selectedGenreId = newGenreId,
                showFilterName = newFilterName
            )
        }
    }

    private fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.isConnected.collect { connected ->
                updateState { copy(isOnline = connected) }
            }
        }
    }

    private fun loadGenres() {
        viewModelScope.launch {
            getGenresUseCase.invoke().handleApiResult(
                onSuccess = { genres -> updateState { copy(filters = genres, genresLoaded = true) } },
                onError = { _, _ -> updateState { copy(genresLoaded = true) } }
            )
        }
    }
}