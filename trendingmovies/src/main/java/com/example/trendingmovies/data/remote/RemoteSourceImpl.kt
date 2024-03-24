package com.example.trendingmovies.data.remote

import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

class RemoteSourceImpl : RemoteSource {

    override suspend fun fetchMovies(): List<Movie> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMovieDetails(): MovieDetails {
        TODO("Not yet implemented")
    }
}