package com.space.movieapp.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.common.api_result.NetworkError
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.getErrorStrings
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.movieapp.feature.home.presentation.vm.HomeViewModel
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val dataState = state.screenDataState) {
        is DataState.Loading -> {
            LoadingScreen()
        }

        is DataState.Success -> {
            HomeContent(
                movies = dataState.data,
                onEvent = viewModel::onEvent
            )
        }

        is DataState.Error -> {
            val (title, description) = getErrorStrings(dataState.errorType)

            ErrorScreen(
                title = stringResource(title),
                description = stringResource(description),
                onRefreshClick = {
                    viewModel.onEvent(HomeEvent.GetPopularMovies)
                }
            )
        }
    }
}

@Composable
private fun HomeContent(
    movies: List<PopularMovieUI>,
    onEvent: (HomeEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
            contentPadding = PaddingValues(Spacing.spacing16)
        ) {
            items(movies, key = { it.id }) { movie ->
                // Also hardcoded parameters for testing
                MovieCatalogueCard(
                    imgUrl = if (!movie.posterPath.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500${movie.posterPath}" else "",
                    genre = "${movie.genreId}",
                    title = movie.title,
                    isFavorite = movie.isFavorite,
                    year = movie.releaseDate,
                    onFavoriteClick = {
                        onEvent(HomeEvent.OnFavoriteClick(movie.id))
                    }
                )
            }
        }
    }
}