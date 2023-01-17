package com.example.artmik.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artmik.data.CountriesRepository
import com.example.artmik.domain.NetworkExceptionHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModelFactory @Inject constructor(
    private val networkExceptionHandler: NetworkExceptionHandler,
    private val repository: CountriesRepository
) : ViewModelProvider.Factory {

    private lateinit var mainViewModel: MainViewModel

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (::mainViewModel.isInitialized) mainViewModel as T else {
            mainViewModel = MainViewModel(networkExceptionHandler, repository)
            mainViewModel as T
        }
    }

}