@file:OptIn(InternalSerializationApi::class)

package com.space.feature.details.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.space.feature.details.presentation.R
import com.space.feature.details.presentation.component.description.MovieDescriptionSection
import com.space.feature.details.presentation.component.details.MovieInfoSection
import com.space.feature.details.presentation.component.poster.MoviePosterSection
import com.space.feature.details.presentation.contract.DetailsEvent
import com.space.feature.details.presentation.contract.DetailsState
import com.space.feature.details.presentation.vm.DetailsVM
import com.space.movie.core.presentation.common.BaseScreen
import com.space.movie.core.presentation.common.DataState
import com.space.ui.component.button.MovieAppHeader
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieTheme
import kotlinx.serialization.InternalSerializationApi
import org.koin.core.parameter.parametersOf

@Composable
fun DetailsScreen(movieId: Int) {
    BaseScreen(
        vmClass = DetailsVM::class,
        parameters = { parametersOf(movieId) },
        content = { state, onEvent ->
            DetailsContent(
                state = state,
                onEvent = onEvent
            )
        }
    )
}

@Composable
private fun DetailsContent(
    state: DetailsState,
    onEvent: (DetailsEvent) -> Unit,
) {
    val colors = MovieTheme.colors

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {
        when (val currentMovieState = state.movieState) {
            is DataState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            is DataState.Success -> {
                val movieData = currentMovieState.data

                Column(modifier = Modifier.fillMaxSize()) {
                    // Header
                    MovieAppHeader(
                        title = stringResource(R.string.details),
                        onBackClick = {
                            onEvent(DetailsEvent.OnBackClick)
                        }
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        // Poster card
                        item {
                            MoviePosterSection(
                                posterUrl = movieData.posterUrl, onTrailerClick = {}
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
                                isFavorite = state.isFavorite,
                                onFavoriteClick = {
                                    onEvent(DetailsEvent.OnFavoriteClick)
                                }
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
                        onRefreshClick = {
                            onEvent(DetailsEvent.OnRefreshClick)
                        }
                    )
                }
            }
        }
    }
}
