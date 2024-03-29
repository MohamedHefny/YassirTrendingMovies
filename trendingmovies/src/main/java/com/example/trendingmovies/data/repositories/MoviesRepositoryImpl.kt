package com.example.trendingmovies.data.repositories

import com.example.trendingmovies.data.remote.RemoteSource
import com.example.trendingmovies.data.remote.RemoteSourceImpl
import com.example.trendingmovies.data.remote.RetrofitClient
import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails
import com.example.trendingmovies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val remoteSource: RemoteSource = RemoteSourceImpl()
) : MoviesRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return remoteSource.fetchMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val remoteMovieDetails = remoteSource.fetchMovieDetails(movieId)
        return mapToMovie(remoteMovieDetails)
    }

    private fun mapToMovie(remoteMovieDetails: MovieDetailsResponse): MovieDetails {
        return with(remoteMovieDetails) {
            val posterUrl = RetrofitClient.POSTERS_BASE_URL + posterPath
            MovieDetails(id, title, overview, posterUrl, releaseDate)
        }
    }

}