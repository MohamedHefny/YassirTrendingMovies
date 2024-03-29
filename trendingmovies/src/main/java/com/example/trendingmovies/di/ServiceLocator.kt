package com.example.trendingmovies.di

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingmovies.data.repositories.MoviesRepositoryImpl
import com.example.trendingmovies.domain.repositories.MoviesRepository
import com.example.trendingmovies.presentation.common.SnackbarDelegate
import com.example.trendingmovies.presentation.trendingmovies.TrendingMoviesViewModel
import kotlinx.coroutines.CoroutineScope

object ServiceLocator {

    internal var snackbarDelegate: SnackbarDelegate? = null

    val trendingMoviesViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val moviesRepository: MoviesRepository = MoviesRepositoryImpl()
            TrendingMoviesViewModel(moviesRepository)
        }
    }

    fun setupSnackbar(
        snackbarHostState: SnackbarHostState? = null,
        coroutineScope: CoroutineScope? = null
    ) {
        if (snackbarDelegate == null)
            snackbarDelegate = SnackbarDelegate(snackbarHostState, coroutineScope)
    }

}