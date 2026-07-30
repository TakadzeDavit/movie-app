package com.space.feature.details.presentation.vm

import androidx.lifecycle.viewModelScope
import com.space.core.domain.usecase.DeleteByIdUseCase
import com.space.core.domain.usecase.InsertFavoriteUseCase
import com.space.core.domain.usecase.IsFavoriteUseCase
import com.space.feature.details.domain.di.DetailsScope
import com.space.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.space.feature.details.presentation.contract.DetailsEvent
import com.space.feature.details.presentation.contract.DetailsState
import com.space.feature.details.presentation.mapper.MovieDetailsDomainMapper
import com.space.movie.core.presentation.common.BaseVM
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.extension.handleApiResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.InternalSerializationApi
import org.koin.core.annotation.InjectedParam
import org.koin.core.component.newScope
import org.koin.core.scope.Scope

@OptIn(InternalSerializationApi::class)
class DetailsVM(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteByIdUseCase: DeleteByIdUseCase,
    private val movieDetailsDomainMapper: MovieDetailsDomainMapper,
    private val isMovieFavoriteUseCase: IsFavoriteUseCase,
    @InjectedParam
    private val movieId: Int
) : BaseVM<DetailsState, DetailsEvent>(DetailsState()) {

    init {
        fetchMovieDetails()
        observeFavoriteStatus()
    }

    override fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.OnFavoriteClick -> toggleFavorite()
            DetailsEvent.OnRefreshClick -> fetchMovieDetails()
            DetailsEvent.OnBackClick -> {
                globalNavigator { pop() }
            }
        }
    }

    private fun toggleFavorite() {
        val currentMovie = (state.value.movieState as? DataState.Success)?.data ?: return

        viewModelScope.launch {
            if (state.value.isFavorite) {
                deleteByIdUseCase(movieId)
            } else {
                insertFavoriteUseCase(movie = movieDetailsDomainMapper.map(currentMovie))
            }
        }
    }

    private fun fetchMovieDetails() {
        launchWithLoader {
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

    private fun observeFavoriteStatus() {
        viewModelScope.launch {
            isMovieFavoriteUseCase.invoke(movieId = movieId).collectLatest { isFavorite ->
                updateState { copy(isFavorite = isFavorite) }
            }
        }
    }
}