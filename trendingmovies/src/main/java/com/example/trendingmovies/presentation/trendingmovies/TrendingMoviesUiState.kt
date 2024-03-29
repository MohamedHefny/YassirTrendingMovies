package com.example.trendingmovies.presentation.trendingmovies

import com.example.trendingmovies.domain.models.Movie

sealed class TrendingMoviesUiState {

    data object Initial : TrendingMoviesUiState()

    data object Loading : TrendingMoviesUiState()

    data class TrendingMovies(val movies: List<Movie>) : TrendingMoviesUiState()

    data class Error(val message: String) : TrendingMoviesUiState()
}