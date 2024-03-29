package com.example.trendingmovies.presentation.moviedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.trendingmovies.di.ServiceLocator
import com.example.trendingmovies.domain.models.MovieDetails
import com.example.trendingmovies.presentation.common.LoadingIndicator
import com.example.trendingmovies.presentation.common.showSnackbar

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    modifier: Modifier = Modifier
) {
    val movieDetailsViewModel: MovieDetailsViewModel =
        viewModel(factory = ServiceLocator.movieDetailsViewModelFactory)

    LaunchedEffect(key1 = movieId) {
        movieDetailsViewModel.getMovieDetails(movieId)
    }

    val movieDetailsState = movieDetailsViewModel.movieDetailsState.collectAsState()

    when (val state = movieDetailsState.value) {
        MovieDetailsUiState.Loading -> LoadingIndicator()
        is MovieDetailsUiState.Error -> showSnackbar(state.message)
        is MovieDetailsUiState.MovieDetailsData -> MovieDetails(movie = state.movie, modifier = modifier)
    }
}

@Composable
fun MovieDetails(
    movie: MovieDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(all = 14.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "${movie.title} poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .heightIn(100.dp, 192.dp)
                    .aspectRatio(0.75f)
            )
        }
        Spacer(modifier = Modifier.padding(top = 14.dp))
        Text(text = movie.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = movie.releaseDate.substringBefore("-"), fontSize = 12.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = movie.overview, fontSize = 12.sp)
    }
}