package com.example.trendingmovies.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trendingmovies.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class TrendingMoviesDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "trending-movies-db"

        @Volatile
        private var database: TrendingMoviesDatabase? = null

        @Synchronized
        fun getDatabase(appContext: Context): TrendingMoviesDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    appContext, TrendingMoviesDatabase::class.java, DB_NAME
                ).build()
            }

            return database!!
        }
    }

    abstract fun moviesDao(): MoviesDao

}