package com.example.trendingmovies.data.repositories

import com.example.trendingmovies.data.local.MoviesLocalSource
import com.example.trendingmovies.data.remote.RemoteSource
import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.data.remote.models.MovieResponse
import com.example.trendingmovies.domain.mappers.ModelMapper
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.domain.models.MovieDetails
import com.example.trendingmovies.domain.repositories.MoviesRepository

class MoviesRepositoryImpl(
    private val remoteSource: RemoteSource,
    private val localSource: MoviesLocalSource,
    private val remoteMovieMapper: ModelMapper<MovieResponse.RemoteMovie, Movie>,
    private val remoteMovieDetailsMapper: ModelMapper<MovieDetailsResponse, MovieDetails>
) : MoviesRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        //Todo: Prioritize local source first
        val movieResult = remoteSource.fetchMovies()
        return movieResult.movies.map { remoteMovieMapper.mapToDomainModel(it) }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val remoteMovieDetails = remoteSource.fetchMovieDetails(movieId)
        return remoteMovieDetailsMapper.mapToDomainModel(remoteMovieDetails)
    }

}