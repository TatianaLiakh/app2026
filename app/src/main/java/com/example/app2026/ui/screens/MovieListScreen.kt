package com.example.app2026.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.app2026.models.Movie
import com.example.app2026.utils.Constants


@Composable
fun MovieListScreen(movieList: List<Movie>,
                    onMovieListItemClicked: (Movie)-> Unit,
                    modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(movieList){ movie ->
            MovieListItemCard(movie,
                onMovieListItemClicked,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun MovieListItemCard(movie: Movie,
                      onMovieListItemClicked: (Movie)-> Unit,modifier: Modifier = Modifier){
    Card(modifier = modifier,
        onClick = {
            onMovieListItemClicked(movie)
        }) {
        Row{
            Box{
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL +
                            Constants.POSTER_IMAGE_BASE_WIDTH +
                            movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .size(width = 92.dp, height = 138.dp),
                    onError = {
                        Log.e("Coil", it.result.throwable.stackTraceToString())
                    }
                )
            }
            Column{
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer( modifier = Modifier.size(8.dp))

                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer( modifier = Modifier.size(8.dp))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer( modifier = Modifier.size(8.dp))

            }
        }
    }
}
