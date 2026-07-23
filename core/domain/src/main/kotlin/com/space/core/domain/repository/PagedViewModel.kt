package com.space.core.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PagedViewModel<T : Any> {
    val pagingFlow: Flow<PagingData<T>>
}