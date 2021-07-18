package com.example.composeexample.domain.result

import com.example.composeexample.domain.response.event.ResponseEvent
import kotlinx.coroutines.flow.flow
import java.lang.Exception

sealed class Result<out T: Any> {
    data class Success<T : Any>(val data: T, val dataSourceType: DataSourceType) : Result<T>()
    data class ServerError(val code: Int, val cause: Exception) : Result<Nothing>()
    data class LocalException(val cause: Exception) : Result<Nothing>()
    object CacheIsEmpty : Result<Nothing>()
    data class NetworkError(val cause: Exception) : Result<Nothing>()
}