package com.example.artmik.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface NetworkExceptionHandler {

    val exceptionsFlow: SharedFlow<String>

    suspend fun handle(message: String)

    class Base : NetworkExceptionHandler {

        private val mutableSharedFlow = MutableSharedFlow<String>()

        override val exceptionsFlow: SharedFlow<String>
            get() = mutableSharedFlow.asSharedFlow()

        override suspend fun handle(message: String) {
            mutableSharedFlow.emit(message)
        }

    }

}