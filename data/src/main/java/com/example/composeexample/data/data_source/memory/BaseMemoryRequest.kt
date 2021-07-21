package com.example.composeexample.data.data_source.memory

import com.example.composeexample.data.data_source.cache.CacheResult

interface BaseMemoryRequest<T: Any> {

    val memoryStorage: MemoryStorage<*>

    fun makeRequest(): MemoryResult<T>
}