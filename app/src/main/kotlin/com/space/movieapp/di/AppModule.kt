package com.space.movieapp.di

import com.space.movieapp.ui.vm.MainVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainVM)
}