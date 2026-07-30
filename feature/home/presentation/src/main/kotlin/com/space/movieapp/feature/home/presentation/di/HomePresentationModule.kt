package com.space.movieapp.feature.home.presentation.di

import com.space.movie.feature.home.domain.di.HomeScope
import com.space.movie.feature.home.domain.usecase.genres.FilterMoviesUseCase
import com.space.movie.feature.home.domain.usecase.genres.GetGenresUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.GetPopularMoviesUseCase
import com.space.movie.feature.home.domain.usecase.movies.SearchMoviesUseCase
import com.space.movieapp.feature.home.presentation.mapper.MovieDomainMapper
import com.space.movieapp.feature.home.presentation.mapper.PopularMovieUiMapper
import com.space.movieapp.feature.home.presentation.vm.HomeVM
import org.koin.dsl.module
import org.koin.plugin.module.dsl.factory
import org.koin.plugin.module.dsl.scoped
import org.koin.plugin.module.dsl.viewModel

val homePresentationModule = module {
    scope<HomeScope> {
        // mapper
        factory<PopularMovieUiMapper>()
        factory<MovieDomainMapper>()

        // use case
        scoped<GetPopularMoviesUseCase>()
        scoped<GetGenresUseCase>()
        scoped<SearchMoviesUseCase>()
        scoped<FilterMoviesUseCase>()
        scoped<GetMoviesUseCase>()

        // vm
        viewModel<HomeVM>()
    }
}