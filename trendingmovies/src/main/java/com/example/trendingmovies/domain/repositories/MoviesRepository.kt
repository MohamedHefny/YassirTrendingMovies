package com.example.trendingmovies.domain.repositories

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

interface MoviesRepository {

    suspend fun getTrendingMovies(): List<Movie>

    suspend fun getMovieDetails(movieId: Int): MovieDetails

}