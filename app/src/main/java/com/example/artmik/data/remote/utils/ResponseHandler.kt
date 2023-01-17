package com.example.artmik.data.remote.utils

import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

private const val SOCKET_TIME_OUT_CODE = -1

interface ResponseHandler {

    fun <T> handleSuccess(data: T): Resource<T>

    fun <T> handleException(e: Exception): Resource<T>

    class Base : ResponseHandler {

        private fun <T> parseError(response: Response<T>): Error {
            response.errorBody()?.let { body ->
                val jsonObject = JSONObject(body.string())
                val errorObject = jsonObject.getJSONObject("error")
                val code = errorObject.getInt("code")
                val message = errorObject.getString("message")
                return Error(code, message)
            }
            return Error(400, "Unknown Error 400")
        }

        private fun getErrorMessage(code: Int, message: String?): String {
            return when (code) {
                401 -> "Unauthorised"
                404 -> "Page not found"
                else -> "Code: $code;\nMessage: $message"
            }
        }

        override fun <T> handleSuccess(data: T): Resource<T> {
            return Resource.Success(data)
        }

        override fun <T> handleException(e: Exception): Resource<T> {
            return when (e) {
                is HttpException -> {
                    val parsedError = parseError(e.response()!!)
                    Resource.Error(message = parsedError.message, data = null)
                }
                is SocketTimeoutException -> {
                    val code = SOCKET_TIME_OUT_CODE
                    Resource.Error(
                        message = getErrorMessage(code, e.message),
                        data = null
                    )
                }
                else -> {
                    Resource.Error(
                        message = getErrorMessage(Int.MAX_VALUE, e.message),
                        data = null
                    )
                }
            }
        }
    }
}


