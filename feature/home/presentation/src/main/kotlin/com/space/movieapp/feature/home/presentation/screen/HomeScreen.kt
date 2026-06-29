package com.space.movieapp.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.space.common.api_result.NetworkError
import com.space.common.exception.PagingException
import com.space.common.exception.toYear
import com.space.movie.core.presentation.common.getErrorStrings
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.movieapp.feature.home.presentation.vm.HomeViewModel
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.error.NetworkStatusBanner
import com.space.ui.component.loader.BottomCircularProgress
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyPagingItems = state.moviesPagedData.collectAsLazyPagingItems()

    when (val refreshState = lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingScreen()
        }

        is LoadState.Error -> {
            val exception = refreshState.error as? PagingException
            val (title, description) = getErrorStrings(exception?.errorType ?: NetworkError.UNKNOWN)

            ErrorScreen(
                title = stringResource(title),
                description = stringResource(description),
                onRefreshClick = {
                    viewModel.onEvent(HomeEvent.GetPopularMovies)
                }
            )
        }

        is LoadState.NotLoading -> {

            HomeContent(
                lazyPagingItems = lazyPagingItems,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@Composable
private fun HomeContent(
    lazyPagingItems: LazyPagingItems<PopularMovieUI>,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    LaunchedEffect(state.isOnline) {
        if (state.isOnline && lazyPagingItems.loadState.append is LoadState.Error) {
            lazyPagingItems.retry()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16),
            verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
            contentPadding = PaddingValues(Spacing.spacing16)
        ) {
            items(
                count = lazyPagingItems.itemCount,
                key = { index ->
                    val movie = lazyPagingItems[index]
                    "${movie?.id}_$index"
                }
            ) { index ->
                val movie = lazyPagingItems[index]

                if (movie != null) {
                    // hardcoded parameters for testing
                    MovieCatalogueCard(
                        imgUrl = if (!movie.posterPath.isNullOrEmpty()) "https://image.tmdb.org/t/p/w500${movie.posterPath}" else "",
                        genre = "${movie.genreId}",
                        title = movie.title,
                        isFavorite = movie.isFavorite,
                        year = movie.releaseDate.toYear(),
                        onFavoriteClick = {
                            onEvent(HomeEvent.OnFavoriteClick(movie.id))
                        }
                    )
                }
            }

            if (lazyPagingItems.loadState.append is LoadState.Loading) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    BottomCircularProgress()
                }
            }
        }

        NetworkStatusBanner(
            isOnline = state.isOnline,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}