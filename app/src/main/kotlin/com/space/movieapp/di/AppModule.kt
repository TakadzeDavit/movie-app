package com.space.movieapp.di

import com.space.common.network.NetworkObserver
import com.space.movieapp.core.network.observer.NetworkObserverImpl
import com.space.movieapp.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<NetworkObserver> { NetworkObserverImpl(androidContext()) }
    viewModelOf(::MainActivityViewModel)
}