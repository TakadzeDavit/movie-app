package com.space.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.core.database.dao.FavoriteDao
import com.space.core.database.dao.GenreDao
import com.space.core.database.entity.FavoriteMovieEntity
import com.space.core.database.entity.GenreEntity

@Database(
    entities = [GenreEntity::class, FavoriteMovieEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun favoriteDao(): FavoriteDao
}