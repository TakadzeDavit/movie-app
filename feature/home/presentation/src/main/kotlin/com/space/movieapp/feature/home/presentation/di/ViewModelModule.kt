package com.space.movieapp.feature.home.presentation.di

import com.space.movieapp.feature.home.presentation.vm.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<HomeViewModel> { HomeViewModel(getPopularMoviesUseCase = get()) }
}