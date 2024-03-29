package com.example.trendingmovies.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trendingmovies.data.local.entities.MovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieEntity>

    @Insert
    fun insertAll(vararg movies: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}