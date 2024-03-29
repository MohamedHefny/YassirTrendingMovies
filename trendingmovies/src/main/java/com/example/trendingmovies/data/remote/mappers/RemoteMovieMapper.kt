package com.example.trendingmovies.data.remote.mappers

import com.example.trendingmovies.data.remote.RetrofitClient
import com.example.trendingmovies.data.remote.models.MovieResponse
import com.example.trendingmovies.domain.mappers.ModelMapper
import com.example.trendingmovies.domain.models.Movie

class RemoteMovieMapper : ModelMapper<MovieResponse.RemoteMovie, Movie> {
    override fun mapToDomainModel(from: MovieResponse.RemoteMovie): Movie {
        val posterUrl = RetrofitClient.POSTERS_BASE_URL + from.posterPath
        return with(from) { Movie(id, title, posterUrl, releaseDate) }
    }
}