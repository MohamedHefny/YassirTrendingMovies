package com.example.trendingmovies.data.remote.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val movies: List<RemoteMovie>
) {

    data class RemoteMovie(
        val id: Int,
        @SerializedName("original_title")
        val title: String,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String
    )
}