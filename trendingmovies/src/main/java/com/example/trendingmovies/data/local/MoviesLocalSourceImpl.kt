package com.example.trendingmovies.data.local

import com.example.trendingmovies.data.local.entities.MovieEntity
import com.example.trendingmovies.data.local.room.TrendingMoviesDatabase

class MoviesLocalSourceImpl(private val database: TrendingMoviesDatabase) : MoviesLocalSource {
    override suspend fun getMovies(): List<MovieEntity> {
        return database.moviesDao().getAllMovies()
    }

    override suspend fun storeMovies(movies: List<MovieEntity>) {
        movies.forEach { database.moviesDao().insertAll(it) }
    }
}