package com.example.composeexample.data.data_source.cache


interface BaseCacheRequest<T: Any> {

    suspend fun makeRequest(): CacheResult<T>
}