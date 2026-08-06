package com.space.movie.feature.home.domain.usecase.movies

import androidx.paging.PagingData
import com.space.core.domain.model.PopularMovie
import com.space.movie.feature.home.domain.usecase.genres.FilterMoviesUseCase
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val filterMoviesUseCase: FilterMoviesUseCase,
) {
    operator fun invoke(query: String, genreId: Int?): Flow<PagingData<PopularMovie>> {
        return when {
            query.isNotBlank() -> searchMoviesUseCase(query)
            genreId != null -> filterMoviesUseCase(genreId)
            else -> getPopularMoviesUseCase()
        }
    }
}