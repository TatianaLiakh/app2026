@file:kotlin.OptIn(ExperimentalMaterial3Api::class)

package com.example.app2026.ui.screens

import androidx.annotation.OptIn
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import com.example.app2026.viewmodel.MovieDBViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app2026.R
import com.example.app2026.database.Movies

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
enum class MovieDBScreen(@StringRes val title: Int){
    List(title = R.string.app_name),
    Detail(title = R.string.movie_detail)
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieDBAppBar(
    currScreen: MovieDBScreen,
    canNavigateBack:Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {Text(stringResource(currScreen.title))},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons
                            .AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@Composable
fun MovieDBApp(viewModel: MovieDBViewModel = viewModel(),
               navController: NavHostController = rememberNavController()
){
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = MovieDBScreen.valueOf(backStackEntry?.destination?.route
        ?: MovieDBScreen.List.name)

    Scaffold(
        topBar = {
            MovieDBAppBar(currentScreen,
                navController.previousBackStackEntry != null,
                navigateUp = {navController.navigateUp()}
            )
        }
    )
    {
        innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MovieDBScreen.List.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)){
            composable(route = MovieDBScreen.List.name ){
                MovieListScreen(movieList = Movies().getMovies(),
                    onMovieListItemClicked = { movie->
                        viewModel.setSelectedMovie(movie)
                        navController.navigate(MovieDBScreen.Detail.name)
                    },
                    modifier = Modifier.fillMaxSize().padding(16.dp) )
            }

            composable( route = MovieDBScreen.Detail.name ){
                uiState.selectedMovie?.let { movie ->
                    MovieDetailScreen(movie = movie,
                        modifier = Modifier)
                }
            }
        }

    }
}