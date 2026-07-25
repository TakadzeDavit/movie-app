package com.space.movieapp.di

import com.space.movie.core.presentation.common.GlobalLoader
import com.space.movieapp.loader.GlobalLoaderImpl
import com.space.movieapp.ui.vm.MainActivityVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainActivityVM)
    single<GlobalLoader> { GlobalLoaderImpl() }
}