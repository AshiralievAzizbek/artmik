package com.example.artmik.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface NetworkExceptionHandler {

    val exceptionsFlow: SharedFlow<Int>

    suspend fun handle(message: Int)

    class Base : NetworkExceptionHandler {

        private val mutableExtensionsFlow = MutableSharedFlow<Int>()

        override val exceptionsFlow: SharedFlow<Int>
            get() = mutableExtensionsFlow.asSharedFlow()

        override suspend fun handle(message: Int) {
            mutableExtensionsFlow.emit(message)
        }

    }

}