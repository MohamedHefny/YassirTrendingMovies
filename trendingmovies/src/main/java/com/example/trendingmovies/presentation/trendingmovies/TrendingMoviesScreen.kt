package com.example.trendingmovies.presentation.trendingmovies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.trendingmovies.di.ServiceLocator
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.presentation.common.LoadingIndicator
import com.example.trendingmovies.presentation.navigation.TrendingMoviesScreens

@Composable
fun TrendingMoviesScreen(navController: NavController, modifier: Modifier = Modifier) {
    val moviesViewModel: TrendingMoviesViewModel =
        viewModel(factory = ServiceLocator.trendingMoviesViewModelFactory)
    val uiState = moviesViewModel.movieListState.collectAsState()

    when (val state = uiState.value) {
        TrendingMoviesUiState.Initial -> Box(modifier)
        TrendingMoviesUiState.Loading -> LoadingIndicator()
        is TrendingMoviesUiState.Error -> ServiceLocator.snackbarDelegate?.showSnackbar(state.message)
        is TrendingMoviesUiState.TrendingMovies -> TrendingMovies(
            state.movies, navController, modifier
        )
    }
}

@Composable
fun TrendingMovies(
    movies: List<Movie>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(movies.size) {
            val movie = movies[it]
            val onMovieClicked = {
                val detailsScreenRoute = TrendingMoviesScreens.MovieDetails.name
                navController.navigate(detailsScreenRoute + "/${movie.id}")
            }
            MovieItem(movie, modifier = Modifier.clickable { onMovieClicked.invoke() })
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }
}

@Composable
fun MovieItem(movie: Movie, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = movie.posterUrl,
            contentDescription = "${movie.title} poster",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .heightIn(72.dp, 128.dp)
                .aspectRatio(0.75f)
        )
        Column(modifier = Modifier.padding(start = 12.dp, top = 1.dp)) {
            Text(text = movie.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = movie.releaseDate.substringBefore("-"), fontSize = 12.sp)
        }
    }
}