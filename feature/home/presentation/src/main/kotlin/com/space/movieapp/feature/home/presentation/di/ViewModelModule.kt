package com.space.movieapp.feature.home.presentation.di

import com.space.movieapp.feature.home.presentation.vm.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)
}