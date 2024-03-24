package com.example.trendingmovies.domain.models

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String
)
