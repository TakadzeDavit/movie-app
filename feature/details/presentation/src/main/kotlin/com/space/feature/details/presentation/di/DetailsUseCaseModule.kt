package com.space.feature.details.presentation.di

import com.space.feature.details.domain.usecase.GetMovieDetailsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailsUseCaseModule = module {
    factoryOf(::GetMovieDetailsUseCase)
}