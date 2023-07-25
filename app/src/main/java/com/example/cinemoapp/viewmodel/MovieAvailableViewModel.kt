package com.example.cinemoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemoapp.models.MovieAvaiableModel
import com.example.cinemoapp.repository.MovieAvailableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieAvailableViewModel @Inject constructor(
    private val repository: MovieAvailableRepository
):ViewModel() {
    val TAG :String = "MovieAvailableViewModel"

    private val _response = MutableLiveData<MovieAvaiableModel>()
    val responseMovies: LiveData<MovieAvaiableModel>
        get() = _response

    fun getMovies() = viewModelScope.launch {
        repository.getMovies().let {response ->

            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d(TAG,response.code().toString())
            }
        }
    }

}