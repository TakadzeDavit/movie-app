package com.space.movieapp

import android.app.Application
import com.space.core.data.di.favoritesMapperModule
import com.space.core.data.di.favoritesRepositoryModule
import com.space.core.database.di.databaseModule
import com.space.feature.details.data.di.detailsApiServiceModule
import com.space.feature.details.data.di.detailsMapperModule
import com.space.feature.details.data.di.detailsRepositoryModule
import com.space.feature.details.presentation.di.detailsUseCaseModule
import com.space.feature.details.presentation.di.detailsViewModelModule
import com.space.movie.core.presentation.di.coreUseCaseModule
import com.space.movie.feature.home.data.di.apiServiceModule
import com.space.movie.feature.home.data.di.homeDataMapperModule
import com.space.movie.feature.home.data.di.repositoryModule
import com.space.movieapp.core.network.di.networkModule
import com.space.movieapp.feature.favorites.presentation.di.favoritesViewModelModule
import com.space.movieapp.feature.home.presentation.di.homeUiModule
import com.space.movieapp.feature.home.presentation.di.useCaseModule
import com.space.movieapp.feature.home.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)

            modules (
                databaseModule,
                homeUiModule,
                homeDataMapperModule,
                networkModule,
                apiServiceModule,
                repositoryModule,
                coreUseCaseModule,
                useCaseModule,
                viewModelModule,
                detailsViewModelModule,
                detailsRepositoryModule,
                detailsMapperModule,
                detailsApiServiceModule,
                detailsUseCaseModule,
                detailsViewModelModule,
                favoritesRepositoryModule,
                favoritesMapperModule,
                favoritesViewModelModule
            )
        }
    }
}