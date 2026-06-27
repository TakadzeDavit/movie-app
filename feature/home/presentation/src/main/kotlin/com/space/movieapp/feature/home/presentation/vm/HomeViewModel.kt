package com.space.movieapp.feature.home.presentation.vm

import androidx.lifecycle.viewModelScope
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularMovieUiMapper: PopularMovieUiMapper
) : BaseViewModel<HomeState, HomeEvent, EmptySideEffect>(HomeState()) {

    init {
        getPopularMovies()
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetPopularMovies -> getPopularMovies()
            is HomeEvent.OnFavoriteClick -> toggleFavorite(event.movieId)
        }
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            updateState { copy(screenDataState = DataState.Loading) }

            getPopularMoviesUseCase.invoke().handleApiResult(
                onSuccess = { movies ->
                    val mappedMovies = movies.results.map(popularMovieUiMapper::map)

                    updateState { copy(screenDataState = DataState.Success(mappedMovies)) }
                },
                onError = { networkError, message ->
                    updateState {
                        copy(
                            screenDataState = DataState.Error(
                                errorType = networkError,
                                message = message
                            )
                        )
                    }
                }
            )
        }
    }

    private fun toggleFavorite(movieId: Int) {
        val currentState = state.value.screenDataState as? DataState.Success ?: return

        val updatedMovies = currentState.data.map { movie ->
            if (movieId == movie.id) {
                movie.copy(isFavorite = !movie.isFavorite)
            } else {
                movie
            }
        }
        updateState { copy(screenDataState = DataState.Success(updatedMovies)) }
    }
}