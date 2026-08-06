package com.space.movieapp.di

import com.space.core.data.di.coreDataModule
import com.space.core.database.di.databaseModule
import com.space.feature.details.data.di.detailsDataModule
import com.space.feature.details.presentation.di.detailsPresentationModule
import com.space.movie.core.presentation.di.corePresentationModule
import com.space.movie.feature.home.data.di.homeDataModule
import com.space.movieapp.core.network.di.networkModule
import com.space.movieapp.feature.favorites.presentation.di.favoritesPresentationModule
import com.space.movieapp.feature.home.presentation.di.homePresentationModule
import org.koin.dsl.module

val appModule = module {
    includes(
        databaseModule,
        networkModule,
        corePresentationModule,
        coreDataModule,
        favoritesPresentationModule,
        homeDataModule,
        homePresentationModule,
        detailsDataModule,
        detailsPresentationModule,
    )
}