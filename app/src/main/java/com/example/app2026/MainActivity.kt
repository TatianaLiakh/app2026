package com.example.app2026

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.app2026.database.Movies
import com.example.app2026.models.Movie
import com.example.app2026.ui.theme.App2026Theme
import com.example.app2026.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieDBApp(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDBApp(name: String, modifier: Modifier = Modifier) {
    MovieList(movieList = Movies().getMovies())
}
@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(movieList){ movie ->
            MovieListItemCard(movie, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun MovieListItemCard(movie: Movie, modifier: Modifier = Modifier){
    Card(modifier = modifier) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App2026Theme {
        MovieListItemCard(
            movie = Movie(
                2,
                "Captain America: Brave New World",
                "/pzIddUEMWhWzfvLI3TwxUG2wGoi.jpg",
                "/gsQJOfeW45KLiQeEIsom94QPQwb.jpg",
                "2025-02-12",
                "When a group of radical activists take over an energy company's annual gala, seizing 300 hostages, an ex-soldier turned window cleaner suspended 50 storeys up on the outside of the building must save those trapped inside, including her younger brother."
            )
        )
    }
}