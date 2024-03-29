package com.example.yassirtrendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.trendingmovies.di.ServiceLocator
import com.example.trendingmovies.presentation.navigation.TrendingMoviesApp
import com.example.yassirtrendingmovies.ui.theme.YassirTrendingMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YassirTrendingMoviesTheme {
                val coroutineScope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                ServiceLocator.setupSnackbar(snackbarHostState, coroutineScope)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { contentPadding ->
                    TrendingMoviesApp(modifier = Modifier.padding(contentPadding))
                }
            }
        }
    }
}