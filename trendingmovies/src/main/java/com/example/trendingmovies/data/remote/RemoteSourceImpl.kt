package com.example.trendingmovies.data.remote

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

class RemoteSourceImpl(
    private val trendingMoviesService: TrendingMoviesService = RetrofitClient.trendingMoviesService
) : RemoteSource {

    override suspend fun fetchMovies(): List<Movie> {
        return trendingMoviesService.fetchMovies()
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
        return trendingMoviesService.fetchMovieDetails(movieId)
    }
}