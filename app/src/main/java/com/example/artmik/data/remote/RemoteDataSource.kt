package com.example.artmik.data.remote

import com.example.artmik.data.remote.models.Country
import com.example.artmik.data.remote.utils.Resource
import com.example.artmik.data.remote.utils.ResponseHandler
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


interface RemoteDataSource {

    suspend fun getCountries(): Resource<List<Country>>

    class Base(
        private val countriesService: CountriesService,
        private val responseHandler: ResponseHandler,
        private val coroutineContext: CoroutineContext
    ) : RemoteDataSource {

        override suspend fun getCountries(): Resource<List<Country>> =
            withContext(coroutineContext) {
                try { responseHandler.handleSuccess(countriesService.getAllCountries()) }
                catch (e: Exception) { responseHandler.handleException(e) }
            }

    }
}