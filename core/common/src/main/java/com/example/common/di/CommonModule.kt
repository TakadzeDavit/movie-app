package com.example.common.di

import com.example.common.network.HandleResponse
import org.koin.dsl.module

val commonModule = module {
    single { HandleResponse() }
}