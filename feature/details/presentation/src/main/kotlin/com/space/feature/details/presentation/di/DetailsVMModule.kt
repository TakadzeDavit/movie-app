@file:OptIn(InternalSerializationApi::class)

package com.space.feature.details.presentation.di

import com.space.feature.details.presentation.mapper.MovieDetailsDomainMapper
import com.space.feature.details.presentation.vm.DetailsVM
import kotlinx.serialization.InternalSerializationApi
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val detailsVMModule = module {
    viewModel { params ->
        DetailsVM(
            getMovieDetailsUseCase = get(),
            insertFavoriteUseCase = get(),
            deleteByIdUseCase = get(),
            movieDetailsDomainMapper = get(),
            isMovieFavoriteUseCase = get(),
            movieId = params.get()
        )
    }

    singleOf(::MovieDetailsDomainMapper)
}