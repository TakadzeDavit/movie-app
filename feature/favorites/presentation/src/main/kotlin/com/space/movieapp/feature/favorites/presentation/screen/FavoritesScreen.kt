package com.space.movieapp.feature.favorites.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.common.getErrorStrings
import com.space.movieapp.feature.favorites.presentation.R
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesEvent
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesState
import com.space.movieapp.feature.favorites.presentation.vm.FavoritesVM
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieTheme
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
    val colors = MovieTheme.colors
    val typography = MovieTheme.typography

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .statusBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.spacing10),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.favorite_movies),
                color = colors.primaryText,
                style = typography.titleMedium
            )
        }

        when (val movieState = state.favoriteMovies) {
            is DataState.Error -> {
                val (title, description) = getErrorStrings(movieState.errorType)

                ErrorScreen(
                    title = stringResource(title),
                    description = stringResource(description),
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
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_no_results),
                            tint = Color.Unspecified,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(Spacing.spacing26))

                        Text(
                            text = stringResource(R.string.no_movies_added_yet),
                            color = colors.textSecondary,
                            style = typography.titleMedium
                        )
                    }
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