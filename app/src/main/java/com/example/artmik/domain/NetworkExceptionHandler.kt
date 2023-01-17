package com.example.artmik.domain

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface NetworkExceptionHandler {

    val exceptionsFlow: SharedFlow<String>

    suspend fun handle(message: String)

    class Base : NetworkExceptionHandler {

        private val mutableExtensionsFlow = MutableSharedFlow<String>()

        init {
            CoroutineScope(Dispatchers.Main).launch {
                mutableExtensionsFlow.collect {
                    Log.d("SHIT", ": $it ")
                }
            }
        }

        override val exceptionsFlow: SharedFlow<String>
            get() = mutableExtensionsFlow.asSharedFlow()

        override suspend fun handle(message: String) {
            mutableExtensionsFlow.emit(message)
        }

    }

}