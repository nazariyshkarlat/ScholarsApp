package com.example.composeexample.data.data_source.memory

import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.domain.result.Result

sealed class MemoryResult<out T: Any> {
    data class Success<T : Any>(val data: T) : MemoryResult<T>()
    object DataNotFound : MemoryResult<Nothing>()
    data class Exception(val cause: java.lang.Exception): MemoryResult<Nothing>()
}

fun <T: Any, R: Any> MemoryResult<T>.toResult(transformation: (T) -> R) : Result<R> = when(this){
    is MemoryResult.DataNotFound -> Result.CacheIsEmpty
    is MemoryResult.Success -> Result.Success(data = transformation(data), dataSourceType = DataSourceType.CACHE)
    is MemoryResult.Exception -> Result.LocalException(cause = cause)
}