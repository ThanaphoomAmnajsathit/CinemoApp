package com.example.cinemoapp.api

import com.example.cinemoapp.models.MovieAvaiableModel
import com.example.cinemoapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getMovies():Response<MovieAvaiableModel>

}