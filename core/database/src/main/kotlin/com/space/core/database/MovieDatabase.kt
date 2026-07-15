package com.space.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.core.database.dao.GenreDao
import com.space.core.database.entity.GenreEntity

@Database(entities = [GenreEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
}