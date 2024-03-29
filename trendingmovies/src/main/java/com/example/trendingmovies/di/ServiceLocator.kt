package com.example.trendingmovies.di

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingmovies.data.remote.RemoteSource
import com.example.trendingmovies.data.remote.RemoteSourceImpl
import com.example.trendingmovies.data.remote.RetrofitClient
import com.example.trendingmovies.data.remote.mappers.RemoteMovieDetailsMapper
import com.example.trendingmovies.data.remote.mappers.RemoteMovieMapper
import com.example.trendingmovies.data.repositories.MoviesRepositoryImpl
import com.example.trendingmovies.domain.repositories.MoviesRepository
import com.example.trendingmovies.presentation.common.SnackbarDelegate
import com.example.trendingmovies.presentation.moviedetails.MovieDetailsViewModel
import com.example.trendingmovies.presentation.trendingmovies.TrendingMoviesViewModel
import kotlinx.coroutines.CoroutineScope

object ServiceLocator {

    private var moviesRepository: MoviesRepository? = null

    internal var snackbarDelegate: SnackbarDelegate? = null

    val trendingMoviesViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { TrendingMoviesViewModel(getOrCreateMoviesRepository()) }
    }

    val movieDetailsViewModelFactory: ViewModelProvider.Factory = viewModelFactory {
        initializer { MovieDetailsViewModel(getOrCreateMoviesRepository()) }
    }

    fun setupSnackbar(
        snackbarHostState: SnackbarHostState? = null,
        coroutineScope: CoroutineScope? = null
    ) {
        if (snackbarDelegate == null)
            snackbarDelegate = SnackbarDelegate(snackbarHostState, coroutineScope)
    }

    private fun getOrCreateMoviesRepository(): MoviesRepository {
        if (moviesRepository == null) {
            val networkService = RetrofitClient.trendingMoviesService
            val remoteSource: RemoteSource = RemoteSourceImpl(networkService)
            moviesRepository = MoviesRepositoryImpl(
                remoteSource, RemoteMovieMapper(), RemoteMovieDetailsMapper()
            )
        }
        return moviesRepository!!
    }

}