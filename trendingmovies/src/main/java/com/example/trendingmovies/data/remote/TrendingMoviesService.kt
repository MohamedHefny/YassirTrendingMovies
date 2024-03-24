package com.example.trendingmovies.data.remote

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path


interface TrendingMoviesService {

    @GET("discover/movie")
    suspend fun fetchMovies(): List<Movie>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") movieId: Int): MovieDetails

}