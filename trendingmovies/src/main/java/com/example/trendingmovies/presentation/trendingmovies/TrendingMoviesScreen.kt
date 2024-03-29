package com.example.trendingmovies.presentation.trendingmovies

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
import coil.compose.AsyncImage
import com.example.trendingmovies.di.ServiceLocator
import com.example.trendingmovies.domain.models.Movie
import com.example.trendingmovies.presentation.common.LoadingIndicator

@Composable
fun TrendingMoviesScreen(modifier: Modifier = Modifier) {
    val moviesViewModel: TrendingMoviesViewModel =
        viewModel(factory = ServiceLocator.trendingMoviesViewModelFactory)
    val uiState = moviesViewModel.movieListState.collectAsState()

    when (val state = uiState.value) {
        TrendingMoviesUiState.Initial -> Box(modifier)
        TrendingMoviesUiState.Loading -> LoadingIndicator()
        is TrendingMoviesUiState.Error -> ServiceLocator.snackbarDelegate?.showSnackbar(state.message)
        is TrendingMoviesUiState.TrendingMovies -> TrendingMovies(modifier, state.movies)
    }
}

@Composable
fun TrendingMovies(modifier: Modifier = Modifier, movies: List<Movie>) {
    LazyColumn(
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, top = 12.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(movies.size) {
            MovieItem(movies[it])
            Spacer(modifier = Modifier.padding(top = 12.dp))
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Row(modifier = Modifier.fillMaxWidth()) {
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