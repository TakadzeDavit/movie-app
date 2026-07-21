package com.space.movieapp.feature.favorites.presentation.vm

import androidx.lifecycle.viewModelScope
import com.space.common.api_result.NetworkError
import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetAllFavoritesUseCase
import com.space.feature.details.api.DetailsFeatureKey
import com.space.movie.core.presentation.common.BaseVM
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.extension.globalNavigator
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesEvent
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoritesVM(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase
) : BaseVM<FavoritesState, FavoritesEvent>(
    FavoritesState()
) {
    init {
        getMovies()
    }

    override fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.RemoveFromFavorites -> deleteUserById(event.movieId)
            is FavoritesEvent.OnRefreshClick -> getMovies()
            is FavoritesEvent.OnNavigateDetails -> {
                globalNavigator {
                    push(DetailsFeatureKey(event.movieId))
                }
            }
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