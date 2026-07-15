package com.space.core.database.di

import androidx.room.Room
import com.space.core.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.jvm.java

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "movie_app_database"
        ).fallbackToDestructiveMigration(false).build()
    }

    single { get<AppDatabase>().genreDao() }
}