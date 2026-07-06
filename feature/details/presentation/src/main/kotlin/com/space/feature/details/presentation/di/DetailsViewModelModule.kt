@file:OptIn(InternalSerializationApi::class)

package com.space.feature.details.presentation.di

import com.space.feature.details.presentation.vm.DetailsViewModel
import kotlinx.serialization.InternalSerializationApi
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModelOf(::DetailsViewModel)
}