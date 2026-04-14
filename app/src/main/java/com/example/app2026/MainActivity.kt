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
import com.example.app2026.ui.screens.MovieDBApp
import com.example.app2026.ui.screens.MovieListItemCard
import com.example.app2026.ui.theme.App2026Theme
import com.example.app2026.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieDBAppStart(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDBAppStart(name: String, modifier: Modifier = Modifier) {
    MovieDBApp()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App2026Theme {
    }
}