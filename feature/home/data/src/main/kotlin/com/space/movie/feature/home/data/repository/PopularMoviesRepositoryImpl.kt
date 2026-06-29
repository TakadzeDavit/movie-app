package com.space.movie.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.data.remote.paging.PopularMoviesPagingSource
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepositoryImpl(
    private val popularMoviesApi: PopularMoviesApiService,
    private val popularMovieDtoMapper: PopularMovieDtoMapper
) : PopularMoviesRepository {
    override fun getMovies(): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    popularMoviesApi = popularMoviesApi,
                    popularMovieDtoMapper = popularMovieDtoMapper
                )
            }
        ).flow
    }
}