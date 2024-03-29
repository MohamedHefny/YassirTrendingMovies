package com.example.trendingmovies.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingmovies.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private var _movieDetailsState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsState = _movieDetailsState.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetails = moviesRepository.getMovieDetails(movieId)
                _movieDetailsState.value = MovieDetailsUiState.MovieDetailsData(movieDetails)
            } catch (e: Exception) {
                _movieDetailsState.value = MovieDetailsUiState.Error(e.message ?: "")
            }
        }
    }

}