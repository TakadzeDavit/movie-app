package com.space.movie.feature.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.space.core.database.dao.GenreDao
import com.space.movie.feature.home.data.mapper.PopularMovieDtoMapper
import com.space.movie.feature.home.data.remote.apiservice.DiscoverApiService
import com.space.movie.feature.home.data.remote.paging.filter.FilterMoviesPagingSource
import com.space.movie.feature.home.domain.model.PopularMovie
import com.space.movie.feature.home.domain.repository.FilterMoviesRepository
import kotlinx.coroutines.flow.Flow

class FilterMoviesRepositoryImpl(
    private val apiService: DiscoverApiService,
    private val dtoMapper: PopularMovieDtoMapper,
    private val genreDao: GenreDao
) : FilterMoviesRepository {
    override fun getFilteredMovies(genreId: Int): Flow<PagingData<PopularMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                FilterMoviesPagingSource(
                    apiService = apiService,
                    genreId = genreId,
                    genreDao = genreDao,
                    dtoMapper = dtoMapper
                )
            }
        ).flow
    }
}