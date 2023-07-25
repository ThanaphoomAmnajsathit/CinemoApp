package com.example.cinemoapp.repository

import com.example.cinemoapp.api.ApiService
import javax.inject.Inject

class MovieAvailableRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getMovies() = apiService.getMovies()
}