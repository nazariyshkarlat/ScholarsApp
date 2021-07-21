package com.example.composeexample.data.data_source.cache

import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.domain.result.Result

sealed class CacheResult<out T: Any> {
    data class Success<T : Any>(val data: T) : CacheResult<T>()
    object DataNotFound : CacheResult<Nothing>()
    data class Exception(val cause: java.lang.Exception): CacheResult<Nothing>()
}

fun <T: Any, R: Any> CacheResult<T>.toResult(transformation: (T) -> R) : Result<R> = when(this){
    is CacheResult.DataNotFound -> Result.CacheIsEmpty
    is CacheResult.Success -> Result.Success(data = transformation(data), dataSourceType = DataSourceType.CACHE)
    is CacheResult.Exception -> Result.LocalException(cause = cause)
}