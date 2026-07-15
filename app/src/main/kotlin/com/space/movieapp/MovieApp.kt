package com.space.movieapp

import android.app.Application
import com.space.movie.feature.home.data.di.apiServiceModule
import com.space.movie.feature.home.data.di.homeDataMapperModule
import com.space.movie.feature.home.data.di.repositoryModule
import com.space.movieapp.core.network.di.networkModule
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
                homeUiModule,
                homeDataMapperModule,
                networkModule,
                apiServiceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}