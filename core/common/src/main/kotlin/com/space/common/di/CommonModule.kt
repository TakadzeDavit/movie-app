package com.space.common.di

import com.space.common.network.HandleResponse
import org.koin.dsl.module

val commonModule = module {
    single { HandleResponse() }
}