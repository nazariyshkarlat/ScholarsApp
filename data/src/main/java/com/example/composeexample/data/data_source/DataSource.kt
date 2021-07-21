package com.example.composeexample.data.data_source

import com.example.composeexample.data.data_source.network.BaseNetworkRequest
import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.data_source.memory.BaseMemoryRequest

abstract class DataSource<FromServer: Any, FromCache: Any, FromMemory: Any> {
    suspend fun getFromCache(request: BaseCacheRequest<FromCache>) = request.makeRequest()
    fun getFromMemory(request: BaseMemoryRequest<FromMemory>) = request.makeRequest()
    suspend fun getFromServer(request: BaseNetworkRequest<FromServer>) = request.makeRequest()

}