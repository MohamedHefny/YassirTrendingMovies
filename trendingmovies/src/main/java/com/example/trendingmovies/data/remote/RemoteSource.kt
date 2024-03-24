package com.example.trendingmovies.data.remote

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

interface RemoteSource {

    suspend fun fetchMovies(): List<Movie>

    suspend fun fetchMovieDetails(): MovieDetails

}