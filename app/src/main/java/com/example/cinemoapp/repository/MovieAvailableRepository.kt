package com.example.cinemoapp.repository
import com.example.cinemoapp.api.ApiService
import com.example.cinemoapp.models.MovieAvaiableModel
import com.example.cinemoapp.utils.UiState
import javax.inject.Inject

class MovieAvailableRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMovies(result: (UiState<MovieAvaiableModel>) -> Unit){
        val response = apiService.getMovies()
        if (response.isSuccessful && response.body() != null){
            result.invoke(UiState.Success(response.body()!!))
        }else{
            result.invoke(UiState.Failure(response.code().toString()))
        }
    }
}