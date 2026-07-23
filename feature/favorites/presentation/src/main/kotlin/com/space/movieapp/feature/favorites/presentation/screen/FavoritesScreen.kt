package com.space.movieapp.feature.favorites.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.toUiModel
import com.space.movieapp.feature.favorites.presentation.component.EmptyFavoriteScreen
import com.space.movieapp.feature.favorites.presentation.component.FavoritesHeader
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesEvent
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesState
import com.space.movieapp.feature.favorites.presentation.vm.FavoritesVM
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesVM = koinViewModel(),
    onNavigateDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritesContent(
        state = state,
        onEvent = viewModel::onEvent,
        onMovieClick = { onNavigateDetails(it) }
    )
}

@Composable
private fun FavoritesContent(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit,
    onMovieClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .statusBarsPadding()
    ) {
        FavoritesHeader()

        when (val movieState = state.favoriteMovies) {
            is DataState.Error -> {
                val errorUiModel = movieState.errorType.toUiModel()

                ErrorScreen(
                    title = stringResource(errorUiModel.titleResId),
                    description = stringResource(errorUiModel.descriptionResId),
                    onRefreshClick = {
                        onEvent(FavoritesEvent.OnRefreshClick)
                    }
                )
            }

            is DataState.Loading -> {
                LoadingScreen()
            }

            is DataState.Success -> {
                if (movieState.data.isEmpty()) {
                    EmptyFavoriteScreen()

                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = Spacing.spacing16),
                        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16),
                        verticalArrangement = Arrangement.spacedBy(Spacing.spacing16)
                    ) {
                        items(movieState.data, key = { it.id }) { movie ->
                            MovieCatalogueCard(
                                modifier = Modifier.animateItem(),
                                imgUrl = movie.posterPath ?: "",
                                genre = movie.genre,
                                title = movie.title,
                                isFavorite = movie.isFavorite,
                                year = movie.releaseDate,
                                showFilterName = movie.genre,
                                onFavoriteClick = {
                                    onEvent(FavoritesEvent.RemoveFromFavorites(movie.id))
                                },
                                onCardClick = { onMovieClick(movie.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}