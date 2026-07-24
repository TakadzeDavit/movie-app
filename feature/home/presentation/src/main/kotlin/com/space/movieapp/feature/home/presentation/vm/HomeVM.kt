package com.space.movieapp.feature.home.presentation.vm

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.space.common.network.NetworkObserver
import com.space.core.domain.model.PopularMovie
import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetFavoriteIdsUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.feature.details.api.DetailsFeatureKey
import com.space.movie.core.presentation.common.BaseVM
import com.space.movie.core.presentation.extension.globalNavigator
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movie.feature.home.domain.usecase.genres.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetMoviesUseCase
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.mapper.MovieDomainMapper
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class HomeVM(
    private val getGenresUseCase: GetGenresUseCase,
    private val popularMovieUiMapper: PopularMovieUiMapper,
    private val deleteByIdUseCase: DeleteByIdUseCase,
    private val networkObserver: NetworkObserver,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val movieDomainMapper: MovieDomainMapper,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseVM<HomeState, HomeEvent>(HomeState()) {

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnFavoriteClick -> toggleFavorite(event.movie)
            is HomeEvent.OnFilterIconClick -> updateState {
                copy(areFiltersExpanded = !areFiltersExpanded)
            }

            is HomeEvent.ResetSearch -> {
                state.value.searchState.clearText()
            }

            is HomeEvent.OnFilterClick -> onFilterClick(event.genreId)
            is HomeEvent.OnNavigateDetails -> {
                globalNavigator { push(DetailsFeatureKey(event.movieId)) }
            }
        }
    }

    init {
        loadGenres()
        observeNetwork()
    }

    @OptIn(FlowPreview::class)
    private val debouncedQueryFlow = snapshotFlow { state.value.searchState.text }
        .map { it.toString() }
        .debounce(500.milliseconds)
        .distinctUntilChanged()

    private val genreIdFlow = state
        .map { it.selectedGenreId }
        .distinctUntilChanged()

    private val genresLoadedFlow = state
        .map { it.genresLoaded }
        .distinctUntilChanged()

    private val favoriteIdsFlow =
        getFavoriteIdsUseCase.invoke()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val basePagedFlow: Flow<PagingData<PopularMovie>> = combine(
        genresLoadedFlow, debouncedQueryFlow, genreIdFlow
    ) { genresLoaded, query, genreId ->
        if (genresLoaded) query to genreId else null
    }
        .filterNotNull()
        .distinctUntilChanged()
        .flatMapLatest { (query, genreId) -> getMoviesUseCase(query, genreId) }
        .cachedIn(viewModelScope)


    val pagingFlow: Flow<PagingData<PopularMovieUI>> = combine(
        basePagedFlow,
        favoriteIdsFlow
    ) { pagingData, favoriteIds ->
        pagingData.map { movie -> popularMovieUiMapper.map(movie, favoriteIds) }
    }

    /**
     * Adds or removes movie from favorites based on current [PopularMovieUI.isFavorite] flag.
     */
    private fun toggleFavorite(movie: PopularMovieUI) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                deleteByIdUseCase.invoke(movie.id)
            } else {
                val domainModel = movieDomainMapper.map(movie)
                insertFavoriteUseCase(movie = domainModel)
            }
        }
    }

    /**
     * Toggles genre selection. Selecting the same genre twice clears the filter.
     * Also updates [HomeState.showFilterNameOnCard] so all cards show
     * the selected genre name instead of their own.
     */
    private fun onFilterClick(genreId: Int) {
        updateState {
            val newGenreId = if (selectedGenreId == genreId) null else genreId
            val newFilterName = if (newGenreId != null) {
                filters.firstOrNull { it.id == newGenreId }?.name
            } else null

            copy(
                selectedGenreId = newGenreId,
                showFilterNameOnCard = newFilterName
            )
        }
    }

    /**
     * Observes network connectivity and updates [HomeState.isOnline].
     * Also used to show no-internet banner at the bottom of the list.
     */
    private fun observeNetwork() {
        viewModelScope.launch {
            networkObserver.isConnected.collectLatest { connected ->
                updateState { copy(isOnline = connected) }
            }
        }
    }

    /**
     * Fetches genres on startup. Movies paging starts only after genres are cached in DB.
     */
    private fun loadGenres() {
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
                onError = { _, _ -> updateState { copy(genresLoaded = true) } }
            )
        }
    }
}