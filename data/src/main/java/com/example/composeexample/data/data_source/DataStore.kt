package com.example.composeexample.data.data_source

import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.data_source.memory.BaseMemoryRequest
import com.example.composeexample.data.data_source.network.BaseNetworkRequest

abstract class DataStore<ToNetwork: Any, ToCache: Any, ToMemory: Any> {
    suspend fun saveToCache(request: BaseCacheRequest<ToMemory>) = request.makeRequest()
    fun saveToMemory(request: BaseMemoryRequest<List<ToCache>>) = request.makeRequest()
    suspend fun saveToServer(request: BaseNetworkRequest<ToNetwork>) = request.makeRequest()

}