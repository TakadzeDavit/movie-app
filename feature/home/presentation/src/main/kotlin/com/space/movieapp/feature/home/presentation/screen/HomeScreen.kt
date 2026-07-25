package com.space.movieapp.feature.home.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.space.core.domain.model.Genre
import com.space.movie.core.presentation.common.BasePagedScreen
import com.space.movieapp.feature.home.presentation.component.AutoRetryOnNetworkRestore
import com.space.movieapp.feature.home.presentation.component.HomeErrorScreen
import com.space.movieapp.feature.home.presentation.component.HomeHeaderSection
import com.space.movieapp.feature.home.presentation.component.MovieGridSection
import com.space.movieapp.feature.home.presentation.contract.HomeEvent
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFavoriteClick
import com.space.movieapp.feature.home.presentation.contract.HomeEvent.OnFilterClick
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.movieapp.feature.home.presentation.vm.HomeVM
import com.space.ui.component.error.EmptyResultView
import com.space.ui.component.loader.LoadingScreen
import com.space.ui.theme.MovieAppTheme
import com.space.ui.theme.MovieTheme.colors
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen() {
    BasePagedScreen(
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
    Column(modifier = Modifier.fillMaxSize()) {
        HomeHeaderSection(
            searchState = state.searchState,
            areFiltersExpanded = state.areFiltersExpanded,
            filters = state.filters,
            selectedGenreId = state.selectedGenreId,
            onFilterClick = { onEvent(OnFilterClick(it)) },
            onFilterIconClick = { onEvent(HomeEvent.OnFilterIconClick) }
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
                AutoRetryOnNetworkRestore(
                    isOnline = state.isOnline,
                    lazyPagingItems = lazyPagingItems
                )

                if (lazyPagingItems.itemCount == 0) {
                    EmptyResultView()
                } else {
                    MovieGridSection(
                        lazyPagingItems = lazyPagingItems,
                        state = state,
                        modifier = Modifier.weight(1f),
                        onCardClick = { onEvent(HomeEvent.OnNavigateDetails(movieId = it)) },
                        onFavoriteClick = { onEvent(OnFavoriteClick(movie = it)) },
                    )
                }
            }

            is LoadState.Error -> {
                HomeErrorScreen(
                    lazyPagingItems = lazyPagingItems,
                    resetSearch = { onEvent(HomeEvent.ResetSearch) }
                )
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