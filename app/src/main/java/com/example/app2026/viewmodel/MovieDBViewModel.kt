package com.example.app2026.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app2026.database.MovieDUIState
import com.example.app2026.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDBViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MovieDUIState())

    val uiState: StateFlow<MovieDUIState> = _uiState.asStateFlow()

    fun setSelectedMovie(movie: Movie){
        _uiState.update { state -> state.copy(selectedMovie = movie) }
    }
}