package com.space.movie.core.presentation.extension

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.space.common.exception.PagingException

val LazyPagingItems<*>.refreshException: PagingException?
    get() = (loadState.refresh as? LoadState.Error)?.error as? PagingException