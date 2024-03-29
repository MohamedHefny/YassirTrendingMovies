package com.example.trendingmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
)