package com.example.trendingmovies.presentation.moviedetails

import com.example.trendingmovies.domain.models.MovieDetails

sealed class MovieDetailsUiState {

    data object Loading : MovieDetailsUiState()

    data class MovieDetailsData(val movie: MovieDetails) : MovieDetailsUiState()

    data class Error(val message: String) : MovieDetailsUiState()

}