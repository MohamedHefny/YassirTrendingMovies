package com.example.trendingmovies.data.remote

import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.data.remote.models.MovieResponse

interface RemoteSource {

    suspend fun fetchMovies(): MovieResponse

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsResponse

}