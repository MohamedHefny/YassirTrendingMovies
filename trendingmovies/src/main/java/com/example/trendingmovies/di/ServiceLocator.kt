package com.example.trendingmovies.di

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingmovies.data.repositories.MoviesRepositoryImpl
import com.example.trendingmovies.domain.repositories.MoviesRepository
import com.example.trendingmovies.presentation.common.SnackbarDelegate
import com.example.trendingmovies.presentation.moviedetails.MovieDetailsViewModel
import com.example.trendingmovies.presentation.trendingmovies.TrendingMoviesViewModel
import kotlinx.coroutines.CoroutineScope

object ServiceLocator {

    private val moviesRepository: MoviesRepository = MoviesRepositoryImpl()


    internal var snackbarDelegate: SnackbarDelegate? = null

    val trendingMoviesViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { TrendingMoviesViewModel(moviesRepository) }
    }

    val movieDetailsViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { MovieDetailsViewModel(moviesRepository) }
    }

    fun setupSnackbar(
        snackbarHostState: SnackbarHostState? = null,
        coroutineScope: CoroutineScope? = null
    ) {
        if (snackbarDelegate == null)
            snackbarDelegate = SnackbarDelegate(snackbarHostState, coroutineScope)
    }

}