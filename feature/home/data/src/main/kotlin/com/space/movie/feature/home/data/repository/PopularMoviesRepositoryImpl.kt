package com.space.movie.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.space.core.database.dao.GenreDao
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.data.remote.paging.movie.PopularMoviesPagingSource
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.repository.PopularMoviesRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesRepositoryImpl(
    private val popularMoviesApi: PopularMoviesApiService,
    private val popularMovieDtoMapper: PopularMovieDtoMapper,
    private val genreDao: GenreDao
) : PopularMoviesRepository {
    override fun getMovies(): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PopularMoviesPagingSource(
                    popularMoviesApi = popularMoviesApi,
                    popularMovieDtoMapper = popularMovieDtoMapper,
                    genreDao = genreDao
                )
            }
        ).flow
    }
}