package com.space.feature.details.presentation.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.space.common.api_result.ApiResult
import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.GetFavoriteIdsUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.space.feature.details.presentation.contract.DetailsEvent
import com.space.feature.details.presentation.contract.DetailsState
import com.space.feature.details.presentation.mapper.MovieDetailsDomainMapper
import com.space.movie.core.presentation.common.BaseViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.EmptySideEffect
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movieapp.core.navigation.Route
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
class DetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
    private val movieDetailsDomainMapper: MovieDetailsDomainMapper,
) : BaseViewModel<DetailsState, DetailsEvent, EmptySideEffect>(DetailsState()) {

    private val detailsArgs = savedStateHandle.toRoute<Route.Details>()
    private val movieId: Int = detailsArgs.movieId

    init {
        fetchMovieDetails()
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.OnFavoriteClick -> toggleFavorite()
        }
    }

    private fun toggleFavorite() {
        val currentMovie = (state.value.movieState as? DataState.Success)?.data ?: return

        viewModelScope.launch {
            if (currentMovie.isFavorite) {
                deleteByIdUseCase(currentMovie.id)
            } else {
                insertFavoriteUseCase(movie = movieDetailsDomainMapper.map(currentMovie))
            }
        }
    }

    private fun fetchMovieDetails() {
        val favoriteIdsFlow = getFavoriteIdsUseCase.invoke()
            .map { it.toSet() }
            .distinctUntilChanged()

        val movieDetailsFlow = getMovieDetailsUseCase.invoke(movieId = movieId)

        viewModelScope.launch {
            combine(movieDetailsFlow, favoriteIdsFlow) { apiResult, favoriteIds ->
                apiResult to favoriteIds
            }.collect { (apiResult, favoriteIds) ->
                when (apiResult) {
                    is ApiResult.Error -> {
                        updateState {
                            copy(
                                movieState = DataState.Error(
                                    errorType = apiResult.errorType,
                                    message = apiResult.message
                                )
                            )
                        }
                    }

                    is ApiResult.Loading -> Unit
                    is ApiResult.Success -> {
                        updateState {
                            copy(
                                movieState = DataState.Success(
                                    apiResult.data.copy(isFavorite = favoriteIds.contains(movieId))
                                )
                            )
                        }
                    }
                }
            }
        }

//        viewModelScope.launch {
//            getMovieDetailsUseCase.invoke(movieId = movieId).handleApiResult(
//                onSuccess = { apiResult ->
//                    updateState { copy(movieState = DataState.Success(apiResult)) }
//                },
//                onError = { networkError, message ->
//                    updateState {
//                        copy(
//                            movieState = DataState.Error(
//                                errorType = networkError,
//                                message = message
//                            )
//                        )
//                    }
//                }
//            )
//        }
    }
}