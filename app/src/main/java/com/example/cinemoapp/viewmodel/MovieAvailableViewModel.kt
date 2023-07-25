package com.example.cinemoapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemoapp.models.MovieAvaiableModel
import com.example.cinemoapp.models.MoviesItem
import com.example.cinemoapp.repository.MovieAvailableRepository
import com.example.cinemoapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieAvailableViewModel @Inject constructor(
    private val repository: MovieAvailableRepository
):ViewModel() {
    val TAG :String = "MovieAvailableViewModel"

    private val _response = MutableLiveData<UiState<MovieAvaiableModel>>()
    val responseMovies: LiveData<UiState<MovieAvaiableModel>>
        get() = _response

    private val favoriteMovies = mutableListOf<MoviesItem>()

    fun getMovies() = viewModelScope.launch {
        _response.value = UiState.Loading
        repository.getMovies {
            _response.value = it
        }
    }

    fun addToFavorites(movie: MoviesItem) {
        if (!favoriteMovies.contains(movie)) {
            favoriteMovies.add(movie)
        }
    }

    fun getFavoriteMovies(): MutableList<MoviesItem> {
        return favoriteMovies
    }

}