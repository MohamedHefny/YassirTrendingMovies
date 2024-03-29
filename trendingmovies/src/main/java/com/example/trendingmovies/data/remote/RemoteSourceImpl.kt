package com.example.trendingmovies.data.remote

import com.example.trendingmovies.data.remote.models.MovieResponse
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails

class RemoteSourceImpl(
    private val trendingMoviesService: TrendingMoviesService = RetrofitClient.trendingMoviesService
) : RemoteSource {

    override suspend fun fetchMovies(): List<Movie> {
        val movieResult = trendingMoviesService.fetchMovies()
        return mapRemoteMovie(movieResult)
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
        return trendingMoviesService.fetchMovieDetails(movieId)
    }

    private fun mapRemoteMovie(movieResponse: MovieResponse): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponse.movies.forEach {
            val posterUrl = RetrofitClient.POSTERS_BASE_URL + it.posterPath
            movies.add(Movie(it.id, it.title, posterUrl, it.releaseDate))
        }
        return movies
    }
}