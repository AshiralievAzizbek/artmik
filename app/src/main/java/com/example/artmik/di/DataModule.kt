package com.example.artmik.di

import com.example.artmik.data.remote.CountriesService
import com.example.artmik.data.remote.RemoteDataSource
import com.example.artmik.data.remote.utils.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    private const val BASE_URL = "https://restcountries.com/v2/"

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

    @Provides
    fun provideCountriesService(retrofit: Retrofit): CountriesService =
        retrofit.create(CountriesService::class.java)

    @Provides
    fun provideResponseHandler(): ResponseHandler =
        ResponseHandler.Base()

    @Provides
    fun provideRemoteDataSource(
        countriesService: CountriesService,
        responseHandler: ResponseHandler
    ): RemoteDataSource =
        RemoteDataSource.Base(countriesService, responseHandler, Dispatchers.IO)

}