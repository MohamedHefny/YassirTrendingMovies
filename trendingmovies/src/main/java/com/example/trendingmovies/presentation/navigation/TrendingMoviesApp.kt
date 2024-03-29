package com.example.trendingmovies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trendingmovies.presentation.moviedetails.MovieDetailsScreen
import com.example.trendingmovies.presentation.trendingmovies.TrendingMoviesScreen

@Composable
fun TrendingMoviesApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TrendingMoviesScreens.TrendingMovies.name
    ) {
        composable(
            route = TrendingMoviesScreens.TrendingMovies.name
        ) {
            TrendingMoviesScreen(navController, modifier)
        }
        composable(
            route = TrendingMoviesScreens.MovieDetails.name + "/{movie_id}",
            arguments = listOf(navArgument("movie_id") {
                type = NavType.IntType
                defaultValue = -1
                nullable = false
            })
        ) {
            MovieDetailsScreen(movieId = it.arguments!!.getInt("movie_id"))
        }
    }
}