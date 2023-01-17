package com.example.artmik.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artmik.data.CountriesRepository
import com.example.artmik.data.remote.models.Country
import com.example.artmik.domain.NetworkExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val networkExceptionHandler: NetworkExceptionHandler,
    private val repository: CountriesRepository
) :
    ViewModel() {


    private val mutableCountriesFlow = MutableStateFlow<List<Country>?>(null)
    val countriesFlow get() = mutableCountriesFlow.asSharedFlow()

    private val mutableSelectedCountry = MutableStateFlow<Country?>(null)
    val selectedCountry get() = mutableSelectedCountry.asStateFlow()

    init {
        viewModelScope.launch { mutableCountriesFlow.emit(repository.getCountries()) }
    }

    fun getExceptionsFlow() = networkExceptionHandler.exceptionsFlow

    suspend fun loadCountries() {
        mutableCountriesFlow.emit(repository.getCountries())
    }

    suspend fun setSelectedCountry(country: Country) {
        mutableSelectedCountry.emit(country)
    }


}