package com.example.trendingmovies.di

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.trendingmovies.data.local.MoviesLocalSource
import com.example.trendingmovies.data.local.MoviesLocalSourceImpl
import com.example.trendingmovies.data.local.room.TrendingMoviesDatabase
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

    var appContext: Context? = null

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
            if (appContext == null)
                throw Exception("Cannot instantiate movies repository while app context is null")

            val networkService = RetrofitClient.trendingMoviesService
            val remoteSource: RemoteSource = RemoteSourceImpl(networkService)
            val roomDatabase = TrendingMoviesDatabase.getDatabase(appContext!!)
            val localSource: MoviesLocalSource = MoviesLocalSourceImpl(roomDatabase)

            moviesRepository = MoviesRepositoryImpl(
                remoteSource, localSource,
                RemoteMovieMapper(), RemoteMovieDetailsMapper()
            )
        }
        return moviesRepository!!
    }

}