package com.space.feature.details.presentation.di

import com.space.feature.details.domain.di.DetailsScope
import com.space.feature.details.domain.usecase.GetMovieDetailsUseCase
import com.space.feature.details.presentation.mapper.MovieDetailsDomainMapper
import com.space.feature.details.presentation.vm.DetailsVM
import org.koin.dsl.module
import org.koin.plugin.module.dsl.scoped
import org.koin.plugin.module.dsl.viewModel

val detailsPresentationModule = module {
    scope<DetailsScope> {
        viewModel<DetailsVM>()
        scoped<GetMovieDetailsUseCase>()
        scoped<MovieDetailsDomainMapper>()
    }
}