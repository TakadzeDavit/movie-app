package com.space.movieapp.feature.home.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.space.common.api_result.NetworkError
import com.space.movie.core.presentation.extension.refreshException
import com.space.movie.core.presentation.extension.toUiModel
import com.space.ui.component.error.ErrorScreen

@Composable
fun HomeErrorScreen(
    lazyPagingItems: LazyPagingItems<*>,
    resetSearch: () -> Unit
) {
    val errorType = lazyPagingItems.refreshException?.errorType ?: NetworkError.UNKNOWN
    val errorUiModel = errorType.toUiModel()

    Box(modifier = Modifier.fillMaxSize()) {
        ErrorScreen(
            title = stringResource(errorUiModel.titleResId),
            description = stringResource(errorUiModel.descriptionResId),
            onRefreshClick = {
                resetSearch()
                lazyPagingItems.retry()
            }
        )
    }
}