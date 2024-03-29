package com.example.trendingmovies.data.remote.mappers

import com.example.trendingmovies.data.remote.RetrofitClient
import com.example.trendingmovies.data.remote.models.MovieDetailsResponse
import com.example.trendingmovies.domain.mappers.ModelMapper
import com.example.trendingmovies.domain.models.MovieDetails

class RemoteMovieDetailsMapper : ModelMapper<MovieDetailsResponse, MovieDetails> {
    override fun mapToDomainModel(from: MovieDetailsResponse): MovieDetails {
        val posterUrl = RetrofitClient.POSTERS_BASE_URL + from.posterPath
        return with(from) { MovieDetails(id, title, overview, posterUrl, releaseDate) }
    }

}