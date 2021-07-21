package com.example.composeexample.data.data_source.network

import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.domain.result.Result
import java.lang.Exception

sealed class ServerResult<out T: Any> {
    data class Success<T : Any>(val data: T) : ServerResult<T>()
    data class ServerError(val code: Int, val cause: Exception) : ServerResult<Nothing>()
    data class LocalException(val cause: Exception) : ServerResult<Nothing>()
    data class NetworkError(val cause: Exception) : ServerResult<Nothing>()
}

fun <T: Any, R: Any>ServerResult<T>.toResult(transformation: (T) -> R) : Result<R> = when(this){
    is ServerResult.LocalException -> Result.LocalException(cause = cause)
    is ServerResult.NetworkError -> Result.NetworkError(cause = cause)
    is ServerResult.ServerError -> Result.ServerError(cause = cause, code = code)
    is ServerResult.Success -> Result.Success(data = transformation(data), dataSourceType = DataSourceType.NETWORK)
}