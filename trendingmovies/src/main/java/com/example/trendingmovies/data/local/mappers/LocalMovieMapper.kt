package com.example.trendingmovies.data.local.mappers

import com.example.trendingmovies.data.local.entities.MovieEntity
import com.example.trendingmovies.data.remote.models.MovieResponse
import com.example.trendingmovies.domain.mappers.ModelMapper

class LocalMovieMapper : ModelMapper<MovieResponse.RemoteMovie, MovieEntity> {
    override fun mapToDomainModel(from: MovieResponse.RemoteMovie): MovieEntity {
        return with(from) { MovieEntity(id, title, posterPath, releaseDate) }
    }
}