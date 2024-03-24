package com.example.trendingmovies.data.repositories

import com.example.trendingmovies.data.remote.RemoteSource
import com.example.trendingmovies.data.remote.RemoteSourceImpl
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails
import com.example.trendingmovies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val remoteSource: RemoteSource = RemoteSourceImpl()
) : MoviesRepository {

    override fun getTrendingMovies(): List<Movie> {
        return remoteSource.fetchMovies()
    }

    override fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteSource.fetchMovieDetails()
    }

}