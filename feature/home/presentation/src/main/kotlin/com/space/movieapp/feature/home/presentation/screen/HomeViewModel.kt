package com.space.movieapp.feature.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.space.movie.core.presentation.base.BaseViewModel
import com.space.movie.core.presentation.extension.handleApiResult
import com.space.movie.feature.home.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel<HomeState, HomeEvent, HomeSideEffect>(HomeState()) {

    init {
        getPopularMovies()
    }

    override fun onEvent(event: HomeEvent) {
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke().handleApiResult(
                onSuccess = {

                },
                onError = {

                },
                onLoading = {

                }
            )
        }
    }
}
