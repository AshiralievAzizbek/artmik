package com.example.artmik.di

import com.example.artmik.data.CountriesRepository
import com.example.artmik.data.remote.RemoteDataSource
import com.example.artmik.domain.NetworkExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkExceptionHandler(): NetworkExceptionHandler =
        NetworkExceptionHandler.Base()

    @Provides
    @Singleton
    fun provideCountriesRepository(
        remoteDataSource: RemoteDataSource,
        networkExceptionHandler: NetworkExceptionHandler
    ): CountriesRepository =
        CountriesRepository.Base(remoteDataSource, networkExceptionHandler)


}