package com.space.movie.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.space.core.database.dao.GenreDao
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.PopularMoviesApiService
import com.space.movie.feature.home.data.remote.paging.search.SearchPagingSource
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.repository.SearchMoviesRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesRepositoryImpl(
    private val apiService: PopularMoviesApiService,
    private val popularMovieDtoMapper: PopularMovieDtoMapper,
    private val genreDao: GenreDao
) : SearchMoviesRepository {
    override fun getMovies(query: String): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    apiService = apiService,
                    query = query,
                    dtoMapper = popularMovieDtoMapper,
                    genreDao = genreDao,
                )
            }
        ).flow
    }
}