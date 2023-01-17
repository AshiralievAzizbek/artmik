package com.example.artmik.data.remote

import com.example.artmik.data.remote.models.Country
import retrofit2.http.GET

interface CountriesService {

    @GET("all")
    suspend fun getAllCountries(): List<Country>

}