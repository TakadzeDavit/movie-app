package com.space.movieapp.feature.favorites.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.space.core.domain.model.PopularMovie
import com.space.movie.core.presentation.common.BaseScreen
import com.space.movie.core.presentation.common.DataState
import com.space.movie.core.presentation.extension.toUiModel
import com.space.movieapp.feature.favorites.presentation.component.EmptyFavoriteScreen
import com.space.movieapp.feature.favorites.presentation.component.FavoritesHeader
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesEvent
import com.space.movieapp.feature.favorites.presentation.contract.FavoritesState
import com.space.movieapp.feature.favorites.presentation.di.FavoriteScope
import com.space.movieapp.feature.favorites.presentation.vm.FavoritesVM
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.ErrorScreen
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.Spacing
import org.koin.core.qualifier.named

@Composable
fun FavoritesScreen() {
    BaseScreen(
        vmClass = FavoritesVM::class,
        scopeQualifier = named<FavoriteScope>(),
        content = { state, onEvent ->
            FavoritesContent(
                state = state,
                onEvent = onEvent
            )
        }
    )
}

@Composable
private fun FavoritesContent(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
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

            is DataState.Loading -> Unit

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
                                onCardClick = {
                                    onEvent(FavoritesEvent.OnNavigateDetails(movie.id))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoritesContentSuccessPreview() {
    val sampleFavoriteMovies = listOf(
        PopularMovie(
            id = 1,
            title = "Inception",
            posterPath = "",
            genre = "Sci-Fi",
            isFavorite = true,
            releaseDate = "2010",
            genreIds = emptyList(),
        ),
        PopularMovie(
            id = 2,
            title = "The Dark Knight",
            posterPath = "",
            genre = "Action",
            isFavorite = true,
            releaseDate = "2008",
            genreIds = emptyList(),
        )
    )

    MovieAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            FavoritesContent(
                state = FavoritesState(
                    favoriteMovies = DataState.Success(sampleFavoriteMovies)
                ),
                onEvent = {}
            )
        }
    }
}