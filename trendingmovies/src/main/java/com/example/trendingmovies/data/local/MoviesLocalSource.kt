package com.example.trendingmovies.data.local

import com.example.trendingmovies.data.local.entities.MovieEntity

interface MoviesLocalSource {

    suspend fun getMovies(): List<MovieEntity>

    suspend fun storeMovies(movies: List<MovieEntity>)

}