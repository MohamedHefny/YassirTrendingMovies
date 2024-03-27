package com.example.trendingmovies.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingmovies.data.repositories.MoviesRepositoryImpl
import com.example.trendingmovies.domain.repositories.MoviesRepository
import com.example.trendingmovies.presentation.home.HomeViewModel

object ServiceLocator {

    val homeViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val moviesRepository: MoviesRepository = MoviesRepositoryImpl()
            HomeViewModel(moviesRepository)
        }
    }

}