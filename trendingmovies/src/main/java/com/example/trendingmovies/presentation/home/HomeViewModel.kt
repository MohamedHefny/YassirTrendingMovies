package com.example.trendingmovies.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trendingmovies.domain.repositories.MoviesRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            val movies = moviesRepository.getTrendingMovies()
            Log.d("TrendingMovies", "Movies list: $movies")
        }
    }

}