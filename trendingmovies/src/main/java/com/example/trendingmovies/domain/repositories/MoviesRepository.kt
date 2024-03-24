package com.example.trendingmovies.domain.repositories

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

interface MoviesRepository {

    fun getTrendingMovies(): List<Movie>

    fun getMovieDetails(movieId: Int): MovieDetails

}