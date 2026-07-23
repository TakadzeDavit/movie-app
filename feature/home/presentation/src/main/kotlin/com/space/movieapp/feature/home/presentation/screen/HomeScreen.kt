package com.space.movieapp.feature.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.space.common.api_result.NetworkError
import com.space.common.exception.toYear
import com.space.core.domain.model.Genre
import com.space.movie.core.presentation.common.BasePagedScreen
import com.space.movie.core.presentation.common.toUiModel
import com.space.movie.core.presentation.extension.isRefreshError
import com.space.movie.core.presentation.extension.refreshException
import com.space.movieapp.feature.home.presentation.R
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFavoriteClick
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFilterClick
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.movieapp.feature.home.presentation.vm.HomeVM
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.EmptyResultView
import com.space.ui.component.error.ErrorScreen
import com.space.ui.component.error.NetworkStatusBanner
import com.space.ui.component.loader.BottomCircularProgress
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.component.search.GenreChip
import com.space.ui.component.search.MovieAppSearch
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen() {
    BasePagedScreen (
        vmClass = HomeVM::class,
        getPagingFlow = { it.pagingFlow },
        content = { lazyPagingItems, state, onEvent ->
            HomeContent(
                lazyPagingItems = lazyPagingItems,
                state = state,
                onEvent = onEvent
            )
        }
    )
}

@Composable
private fun HomeContent(
    lazyPagingItems: LazyPagingItems<PopularMovieUI>,
    state: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    if (lazyPagingItems.isRefreshError) {
        val errorType = lazyPagingItems.refreshException?.errorType ?: NetworkError.UNKNOWN
        val errorUiModel = errorType.toUiModel()

        Box(modifier = Modifier.fillMaxSize()) {
            ErrorScreen(
                title = stringResource(errorUiModel.titleResId),
                description = stringResource(errorUiModel.descriptionResId),
                onRefreshClick = {
                    onEvent(HomeEvent.ResetSearch)
                    lazyPagingItems.retry()
                }
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            LaunchedEffect(state.isOnline) {
                if (state.isOnline) {
                    val refreshFailed = lazyPagingItems.loadState.refresh is LoadState.Error
                    val appendFailed = lazyPagingItems.loadState.append is LoadState.Error
                    if (refreshFailed || appendFailed) {
                        lazyPagingItems.retry()
                    }
                }
            }

            MovieAppSearch(
                searchState = state.searchState,
                onFilterClick = { onEvent(HomeEvent.OnFilterIconClick) },
                areFiltersExpanded = state.areFiltersExpanded,
            ) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(Spacing.spacing08),
                    contentPadding = PaddingValues(horizontal = Spacing.spacing16)
                ) {
                    items(
                        items = state.filters,
                        key = { it.id }
                    ) { genre ->
                        val isSelected = genre.id == state.selectedGenreId

                        GenreChip(
                            title = genre.name,
                            isSelected = isSelected,
                            onChipClick = { onEvent(OnFilterClick(genre.id)) }
                        )
                    }
                }
            }

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
                                    style = typography.headlineSmall,
                                    color = colors.primary
                                )

                                Spacer(modifier = Modifier.height(Spacing.spacing16))

                                val isLoading =
                                    lazyPagingItems.loadState.refresh is LoadState.Loading

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
                                    showFilterName = state.showFilterNameOnCard,
                                    title = movie.title,
                                    isFavorite = movie.isFavorite,
                                    year = movie.releaseDate.toYear(),
                                    onFavoriteClick = { onEvent(OnFavoriteClick(movie)) },
                                    onCardClick = { onEvent(HomeEvent.OnNavigateDetails(movie.id)) }
                                )
                            }
                        }

                        if (lazyPagingItems.loadState.append is LoadState.Loading) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                BottomCircularProgress()
                            }
                        }

                        if (!state.isOnline) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                NetworkStatusBanner(isOnline = state.isOnline)
                            }
                        }
                    }
                }

                is LoadState.Error -> Unit
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeContentSuccessPreview() {
    val sampleMovies = listOf(
        PopularMovieUI(
            id = 1,
            title = "Inception",
            posterPath = "",
            genre = "Sci-Fi",
            isFavorite = true,
            releaseDate = "2010-07-16"
        ),
        PopularMovieUI(
            id = 2,
            title = "Spider-Man: Across the Spider-Verse",
            posterPath = "",
            genre = "Action",
            isFavorite = false,
            releaseDate = "2023-06-02"
        )
    )

    MovieAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background)
        ) {
            val lazyPagingItems = flowOf(
                PagingData.from(
                    data = sampleMovies,
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(endOfPaginationReached = false),
                        prepend = LoadState.NotLoading(endOfPaginationReached = true),
                        append = LoadState.NotLoading(endOfPaginationReached = false)
                    )
                )
            ).collectAsLazyPagingItems()

            HomeContent(
                lazyPagingItems = lazyPagingItems,
                state = HomeState(
                    searchState = TextFieldState(),
                    isOnline = true,
                    areFiltersExpanded = true,
                    selectedGenreId = 1,
                    filters = listOf(
                        Genre(id = 1, name = "Action"),
                        Genre(id = 2, name = "Comedy"),
                        Genre(id = 3, name = "Sci-Fi")
                    ),
                    genresLoaded = true
                ),
                onEvent = {}
            )
        }
    }
}