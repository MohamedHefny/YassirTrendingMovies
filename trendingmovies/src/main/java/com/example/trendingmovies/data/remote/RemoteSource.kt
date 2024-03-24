package com.example.trendingmovies.data.remote

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

interface RemoteSource {

    fun fetchMovies(): List<Movie>

    fun fetchMovieDetails(): MovieDetails

}