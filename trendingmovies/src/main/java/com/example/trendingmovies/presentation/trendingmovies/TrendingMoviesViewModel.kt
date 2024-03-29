package com.example.trendingmovies.presentation.trendingmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingmovies.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrendingMoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var _movieListState =
        MutableStateFlow<TrendingMoviesUiState>(TrendingMoviesUiState.Initial)
    val movieListState = _movieListState.asStateFlow()

    init {
        getTrendingMovies()
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            try {
                val movies = moviesRepository.getTrendingMovies()
                _movieListState.value = TrendingMoviesUiState.TrendingMovies(movies)
            } catch (e: Exception) {
                _movieListState.value = TrendingMoviesUiState.Error(e.message ?: "")
            }
        }
    }

}