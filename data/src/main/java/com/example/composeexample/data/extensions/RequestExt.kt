package com.example.composeexample.data.extensions

import com.example.composeexample.data.data_source.api.ServerResult
import io.ktor.client.features.*
import io.ktor.utils.io.errors.*
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

suspend fun <T : Any>(suspend () -> T).handleNetworkRequest(): ServerResult<T> =
    try{
        ServerResult.Success(this())
    }catch(cause: Exception){
        cause.printStackTrace()
        when(cause){
            is ResponseException -> ServerResult.ServerError(code = cause.response.status.value, cause = cause)
            is HttpRequestTimeoutException, is java.net.SocketTimeoutException,
            is UnknownHostException, is SSLException, is EOFException,
            is ConnectException, is io.ktor.utils.io.CancellationException -> ServerResult.NetworkError(cause = cause)
            else -> ServerResult.LocalException(cause = cause)
        }
    }

suspend fun <T : Any>(suspend () -> T).handleCacheRequest(): ServerResult<T> =
    try{
        ServerResult.Success(this())
    }catch(cause: Exception){
        cause.printStackTrace()
        when(cause){
            is ResponseException -> ServerResult.ServerError(code = cause.response.status.value, cause = cause)
            is HttpRequestTimeoutException, is java.net.SocketTimeoutException,
            is UnknownHostException, is SSLException, is EOFException,
            is ConnectException, is io.ktor.utils.io.CancellationException -> ServerResult.NetworkError(cause = cause)
            else -> ServerResult.LocalException(cause = cause)
        }
    }