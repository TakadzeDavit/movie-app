package com.space.movie.feature.home.domain.repository

import androidx.paging.PagingData
import com.space.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface SearchMoviesRepository {
    fun getMovies(query: String): Flow<PagingData<PopularMovie>>
}