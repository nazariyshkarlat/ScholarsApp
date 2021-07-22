package com.example.composeexample.data.data_source.network

import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.domain.result.Result
import java.lang.Exception

sealed class NetworkResult<out T: Any> {
    data class Success<T : Any>(val data: T) : NetworkResult<T>()
    data class ServerError(val code: Int, val cause: Exception) : NetworkResult<Nothing>()
    data class LocalException(val cause: Exception) : NetworkResult<Nothing>()
    data class NetworkError(val cause: Exception) : NetworkResult<Nothing>()
}

fun <T: Any, R: Any>NetworkResult<T>.toResult(transformation: (T) -> R) : Result<R> = when(this){
    is NetworkResult.LocalException -> Result.LocalException(cause = cause)
    is NetworkResult.NetworkError -> Result.NetworkError(cause = cause)
    is NetworkResult.ServerError -> Result.ServerError(cause = cause, code = code)
    is NetworkResult.Success -> Result.Success(data = transformation(data), dataSourceType = DataSourceType.Network)
}