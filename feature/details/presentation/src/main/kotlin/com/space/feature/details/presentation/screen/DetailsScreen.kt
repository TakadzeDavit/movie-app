@file:OptIn(InternalSerializationApi::class)

package com.space.feature.details.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.space.feature.details.presentation.R
import com.space.feature.details.presentation.component.description.MovieDescriptionSection
import com.space.feature.details.presentation.component.details.MovieInfoSection
import com.space.feature.details.presentation.component.poster.MoviePosterSection
import com.space.feature.details.presentation.contract.DetailsState
import com.space.feature.details.presentation.vm.DetailsViewModel
import com.space.movie.core.presentation.common.DataState
import com.space.ui.component.button.MovieAppHeader
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieTheme
import kotlinx.serialization.InternalSerializationApi
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailsContent(
        state = state,
        onBackClick = onBackClick,
    )
}

@Composable
private fun DetailsContent(
    state: DetailsState,
    onBackClick: () -> Unit,
    onTrailerClick: () -> Unit = {}
) {
    val colors = MovieTheme.colors

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        when (val currentMovieState = state.movieState) {
            is DataState.Loading -> {
                LoadingScreen()
            }

            is DataState.Success -> {
                val movieData = currentMovieState.data

                Column(modifier = Modifier.fillMaxSize()) {
                    // Header
                    MovieAppHeader(
                        title = stringResource(R.string.details),
                        onBackClick = onBackClick
                    )

                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        // Poster card
                        item {
                            MoviePosterSection(
                                posterUrl = movieData.posterUrl, onTrailerClick = onTrailerClick
                            )
                        }

                        // Movie details
                        item {
                            MovieInfoSection(
                                title = movieData.title,
                                rating = movieData.rating,
                                genre = movieData.genre,
                                duration = movieData.duration,
                                year = movieData.year,
                                isFavorite = movieData.isFavorite,
                                onFavoriteClick = {}
                            )
                        }

                        // Movie description
                        item {
                            MovieDescriptionSection(description = movieData.overview)
                        }
                    }
                }
            }

            is DataState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ErrorScreen(
                        title = stringResource(R.string.something_went_wrong),
                        description = stringResource(R.string.please_try_again),
                        onRefreshClick = { }
                    )
                }
            }
        }
    }
}
