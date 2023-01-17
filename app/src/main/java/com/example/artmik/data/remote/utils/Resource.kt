package com.example.artmik.data.remote.utils

sealed class Resource<T>(
    val data: T? = null,
    val message: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(data: T?, resId: Int) : Resource<T>(data, resId)
}