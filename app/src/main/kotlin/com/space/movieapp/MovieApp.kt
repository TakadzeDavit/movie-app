package com.space.movieapp

import android.app.Application
import com.space.core.data.di.favoriteDataSourceModule
import com.space.core.data.di.favoritesMapperModule
import com.space.core.data.di.favoritesRepositoryModule
import com.space.core.database.di.databaseModule
import com.space.feature.details.data.di.detailsApiServiceModule
import com.space.feature.details.data.di.detailsDataSourceRemote
import com.space.feature.details.data.di.detailsMapperModule
import com.space.feature.details.data.di.detailsRepositoryModule
import com.space.feature.details.presentation.di.detailsUseCaseModule
import com.space.feature.details.presentation.di.detailsVMModule
import com.space.movie.core.presentation.di.corePresentationModule
import com.space.movie.feature.home.data.di.apiServiceModule
import com.space.movie.feature.home.data.di.homeDataMapperModule
import com.space.movie.feature.home.data.di.homeDataSourceModule
import com.space.movie.feature.home.data.di.repositoryModule
import com.space.movieapp.core.network.di.networkModule
import com.space.movieapp.di.appModule
import com.space.movieapp.feature.favorites.presentation.di.favoritesVMModule
import com.space.movieapp.feature.home.presentation.di.homeUiModule
import com.space.movieapp.feature.home.presentation.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)

            modules (
                appModule,
                databaseModule,
                homeUiModule,
                homeDataMapperModule,
                networkModule,
                homeDataSourceModule,
                repositoryModule,
                apiServiceModule,
                detailsDataSourceRemote,
                corePresentationModule,
                useCaseModule,
                detailsVMModule,
                detailsRepositoryModule,
                detailsMapperModule,
                detailsApiServiceModule,
                detailsUseCaseModule,
                favoritesRepositoryModule,
                favoritesMapperModule,
                favoritesVMModule,
                favoriteDataSourceModule
            )
        }
    }
}