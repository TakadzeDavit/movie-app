package com.space.feature.details.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.space.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.space.feature.details.presentation.contract.DetailsEvent
import com.space.feature.details.presentation.contract.DetailsState
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movieapp.core.navigation.Route
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class DetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<DetailsState, DetailsEvent, EmptySideEffect>(DetailsState()) {

    private val detailsArgs = savedStateHandle.toRoute<Route.Details>()
    val movieId: Int = detailsArgs.movieId

    init {
        fetchMovieDetails()
    }

    override fun onEvent(event: DetailsEvent) {
    }

    private fun fetchMovieDetails() {
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(movieId = movieId).handleApiResult(
                onSuccess = { apiResult ->
                    updateState { copy(movieState = DataState.Success(apiResult)) }
                },
                onError = { networkError, message ->
                    updateState {
                        copy(
                            movieState = DataState.Error(
                                errorType = networkError,
                                message = message
                            )
                        )
                    }
                }
            )
        }
    }
}