package com.example.artmik.view

import androidx.lifecycle.ViewModel
import com.example.artmik.domain.NetworkExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkExceptionHandler: NetworkExceptionHandler) :
    ViewModel() {


}