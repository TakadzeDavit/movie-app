package com.space.common.di

import com.space.common.network.ResponseHandler
import org.koin.dsl.module

val commonModule = module {
    single { ResponseHandler() }
}