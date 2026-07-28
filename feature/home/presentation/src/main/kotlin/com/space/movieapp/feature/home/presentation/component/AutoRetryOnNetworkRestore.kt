package com.space.movieapp.feature.home.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun AutoRetryOnNetworkRestore(
    isOnline: Boolean,
    lazyPagingItems: LazyPagingItems<*>
) {
    LaunchedEffect(isOnline) {
        if (isOnline) {
            val hasError = lazyPagingItems.loadState.refresh is LoadState.Error ||
                    lazyPagingItems.loadState.append is LoadState.Error
            if (hasError) lazyPagingItems.retry()
        }
    }
}
