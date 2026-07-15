package com.space.movieapp.feature.favorites.presentation.vm

import androidx.lifecycle.viewModelScope
import com.space.common.api_result.NetworkError
import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetAllFavoritesUseCase
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesEvent
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase
) : BaseViewModel<FavoritesState, FavoritesEvent, EmptySideEffect>(
    FavoritesState()
) {
    init {
        getMovies()
    }

    override fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.RemoveFromFavorites -> deleteUserById(event.movieId)
            is FavoritesEvent.OnRefreshClick -> getMovies()
        }
    }

    private fun deleteUserById(movieId: Int) {
        viewModelScope.launch {
            deleteByIdUseCase.invoke(movieId)
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            updateState { copy(favoriteMovies = DataState.Loading) }

            getAllFavoritesUseCase.invoke()
                .catch { exception ->
                    updateState {
                        copy(
                            favoriteMovies = DataState.Error(
                                NetworkError.UNKNOWN,
                                exception.message
                            )
                        )
                    }
                }
                .collectLatest { moviesList ->
                    updateState {
                        copy(favoriteMovies = DataState.Success(moviesList))
                    }
                }
        }
    }
}