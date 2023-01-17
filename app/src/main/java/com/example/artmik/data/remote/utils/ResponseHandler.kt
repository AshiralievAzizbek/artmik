package com.example.artmik.data.remote.utils

import com.example.artmik.R
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

interface ResponseHandler {

    fun <T> handleSuccess(data: T): Resource<T>

    fun <T> handleException(e: Exception): Resource<T>

    class Base : ResponseHandler {

        private fun <T> parseError(response: Response<T>): Int {
            response.errorBody()?.let { body ->
                val jsonObject = JSONObject(body.string())
                val errorObject = jsonObject.getJSONObject("error")
                val message: Int = when (errorObject.getInt("code")) {
                    in 400 until 500 -> {
                        R.string.error_client
                    }
                    in 500 until 600 -> {
                        R.string.error_server
                    }
                    else -> {
                        R.string.error_unknown
                    }
                }
                return message
            }
            return R.string.error_unknown
        }

        override fun <T> handleSuccess(data: T): Resource<T> {
            return Resource.Success(data)
        }

        override fun <T> handleException(e: Exception): Resource<T> {
            return when (e) {
                is HttpException -> {
                    val parsedError = parseError(e.response()!!)
                    Resource.Error(resId = parsedError, data = null)
                }
                is SocketTimeoutException -> {
                    Resource.Error(
                        resId = R.string.error_socket_timeout,
                        data = null
                    )
                }
                else -> {
                    Resource.Error(
                        resId = R.string.error_unknown,
                        data = null
                    )
                }
            }
        }
    }
}


