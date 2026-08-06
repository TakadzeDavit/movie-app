package com.space.movie.feature.home.domain.repository

import androidx.paging.PagingData
import com.space.core.domain.model.PopularMovie
import kotlinx.coroutines.flow.Flow

interface FilterMoviesRepository {
    fun getFilteredMovies(genreId: Int) : Flow<PagingData<PopularMovie>>
}