package com.example.trendingmovies.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
)
