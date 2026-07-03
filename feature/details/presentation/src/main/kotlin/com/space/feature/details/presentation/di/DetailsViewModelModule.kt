package com.space.feature.details.presentation.di

import com.space.feature.details.presentation.vm.DetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel<DetailsViewModel> {
        DetailsViewModel()
    }
}