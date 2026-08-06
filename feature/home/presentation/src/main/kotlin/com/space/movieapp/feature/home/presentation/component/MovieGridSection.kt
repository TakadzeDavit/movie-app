package com.space.movieapp.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.space.movie.core.presentation.debounce.rememberOnClick
import com.space.movieapp.feature.home.presentation.R
import com.space.movieapp.feature.home.presentation.contract.HomeState
import com.space.movieapp.feature.home.presentation.model.PopularMovieUI
import com.space.ui.component.card.MovieCatalogueCard
import com.space.ui.component.error.NetworkStatusBanner
import com.space.ui.component.loader.BottomCircularProgress
import com.space.ui.theme.MovieTheme.colors
import com.space.ui.theme.MovieTheme.typography
import com.space.ui.theme.Spacing

@Composable
fun MovieGridSection(
    lazyPagingItems: LazyPagingItems<PopularMovieUI>,
    state: HomeState,
    modifier: Modifier,
    onCardClick: (Int) -> Unit,
    onFavoriteClick: (PopularMovieUI) -> Unit
) {
    LazyVerticalGrid(
        columns = Fixed(2),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.spacing16),
        verticalArrangement = Arrangement.spacedBy(Spacing.spacing16),
        contentPadding = PaddingValues(Spacing.spacing16)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(R.string.movies),
                style = typography.headlineSmall,
                color = colors.primary
            )

            Spacer(modifier = Modifier.height(Spacing.spacing16))
        }

        items(
            count = lazyPagingItems.itemCount
        ) { index ->
            lazyPagingItems[index]?.let { movie ->
                MovieCatalogueCard(
                    imgUrl = movie.posterPath ?: "",
                    genre = movie.genre,
                    showFilterName = state.showFilterNameOnCard,
                    title = movie.title,
                    isFavorite = movie.isFavorite,
                    year = movie.year,
                    onFavoriteClick = { onFavoriteClick(movie) },
                    onCardClick = rememberOnClick { onCardClick(movie.id) }
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