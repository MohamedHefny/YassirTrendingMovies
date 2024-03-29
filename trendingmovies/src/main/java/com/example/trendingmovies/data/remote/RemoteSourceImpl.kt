package com.example.trendingmovies.data.remote

import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.data.remote.models.MovieResponse

class RemoteSourceImpl(private val trendingMoviesService: TrendingMoviesService) : RemoteSource {

    override suspend fun fetchMovies(): MovieResponse {
        return trendingMoviesService.fetchMovies()
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse {
        return trendingMoviesService.fetchMovieDetails(movieId)
    }
}