package com.example.artmik.data

import com.example.artmik.data.remote.RemoteDataSource
import com.example.artmik.data.remote.models.Country
import com.example.artmik.data.remote.utils.Resource
import com.example.artmik.domain.NetworkExceptionHandler

interface CountriesRepository {
    suspend fun getCountries(): List<Country>

    class Base(
        private val remoteDataSource: RemoteDataSource,
        private val networkExceptionHandler: NetworkExceptionHandler
    ) : CountriesRepository {

        override suspend fun getCountries(): List<Country> {
            return when (val resource = remoteDataSource.getCountries()) {
                is Resource.Error -> {
                    networkExceptionHandler.handle(resource.message ?: "R.string.server_error")
                    listOf()
                }
                is Resource.Success -> {
                    resource.data ?: listOf()
                }
            }
        }
    }
}