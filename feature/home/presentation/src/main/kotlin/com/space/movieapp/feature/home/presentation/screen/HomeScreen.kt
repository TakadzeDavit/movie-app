package com.space.movieapp.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
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
import com.space.movieapp.feature.home.presentation.R
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFavoriteClick
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFilterClick
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnSearchQueryChange
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.movieapp.feature.home.presentation.vm.HomeViewModel
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.EmptyResultView
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.error.NetworkStatusBanner
import com.space.ui.component.loader.BottomCircularProgress
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.component.search.MovieAppSearch
import com.space.ui.theme.MovieTheme
import com.space.ui.theme.Spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateDetails: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lazyPagingItems = viewModel.moviesPagedFlow.collectAsLazyPagingItems()

    // Show error screen when initial load fails
    if (lazyPagingItems.loadState.refresh is LoadState.Error) {
        // Get custom PagingException from error state
        val exception =
            (lazyPagingItems.loadState.refresh as LoadState.Error).error as? PagingException

        // Map error type to user-facing title and description strings
        val (title, description) = getErrorStrings(
            exception?.errorType ?: NetworkError.UNKNOWN
        )

        Box(modifier = Modifier.fillMaxSize()) {
            ErrorScreen(
                title = stringResource(title),
                description = stringResource(description),
                onRefreshClick = {
                    viewModel.onEvent(HomeEvent.ResetSearch)
                    lazyPagingItems.retry()
                }
            )
        }
    } else {
        HomeContent(
            lazyPagingItems = lazyPagingItems,
            state = state,
            onEvent = viewModel::onEvent,
            onNavigateDetails = { onNavigateDetails(it) }
        )
    }
}

@Composable
private fun HomeContent(
    lazyPagingItems: LazyPagingItems<PopularMovieUI>,
    state: HomeState,
    onNavigateDetails: (Int) -> Unit,
    onEvent: (HomeEvent) -> Unit
) {
    LaunchedEffect(state.isOnline) {
        if (state.isOnline) {
            val refreshFailed = lazyPagingItems.loadState.refresh is LoadState.Error
            val appendFailed = lazyPagingItems.loadState.append is LoadState.Error
            if (refreshFailed || appendFailed) {
                lazyPagingItems.retry()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        MovieAppSearch(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { onEvent(OnSearchQueryChange(it)) },
            onFilterClick = { onEvent(HomeEvent.OnFilterIconClick) },
            areFiltersExpanded = state.areFiltersExpanded,
            filterOptions = state.filters,
            selectedOptionId = state.selectedGenreId,
            onOptionSelected = { genreId ->
                onEvent(OnFilterClick(genreId))
            },
        )

        when (lazyPagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = Fixed(2),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16),
                    verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
                    contentPadding = PaddingValues(Spacing.spacing16)
                ) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.movies),
                                style = MovieTheme.typography.headlineSmall,
                                color = MovieTheme.colors.primary
                            )

                            Spacer(modifier = Modifier.height(Spacing.spacing16))

                            val isLoading = lazyPagingItems.loadState.refresh is LoadState.Loading

                            if (lazyPagingItems.itemCount == 0 && !isLoading) {
                                EmptyResultView()
                            }
                        }
                    }

                    items(
                        count = lazyPagingItems.itemCount
                    ) { index ->
                        val movie = lazyPagingItems[index]

                        if (movie != null) {
                            MovieCatalogueCard(
                                imgUrl = movie.posterPath ?: "",
                                genre = movie.genre,
                                showFilterName = state.showFilterName,
                                title = movie.title,
                                isFavorite = movie.isFavorite,
                                year = movie.releaseDate.toYear(),
                                onFavoriteClick = { onEvent(OnFavoriteClick(movie.id)) },
                                onCardClick = { onNavigateDetails(movie.id) }
                            )
                        }
                    }

                    if (lazyPagingItems.loadState.append is LoadState.Loading) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            BottomCircularProgress()
                        }
                    }
                }
            }

            is LoadState.Error -> Unit
        }

        NetworkStatusBanner(
            isOnline = state.isOnline,
        )
    }
}