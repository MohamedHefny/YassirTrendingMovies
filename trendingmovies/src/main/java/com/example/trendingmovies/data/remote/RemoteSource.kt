package com.example.trendingmovies.data.remote

import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.domain.models.Movie

interface RemoteSource {

    suspend fun fetchMovies(): List<Movie>

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse

}