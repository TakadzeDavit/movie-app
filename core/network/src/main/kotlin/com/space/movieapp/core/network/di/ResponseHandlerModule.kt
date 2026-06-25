package com.space.movieapp.core.network.di

import com.space.movieapp.core.network.apicall.ResponseHandler
import org.koin.dsl.module

val responseHandlerModule = module {
    single { ResponseHandler() }
}