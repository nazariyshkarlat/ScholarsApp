package com.example.composeexample.data.data_source

import com.example.composeexample.data.data_source.api.BaseNetworkRequest
import com.example.composeexample.data.data_source.cache.BaseCacheRequest

interface DataSource<FromServer: Any, FromCache: Any> {
    val fromCache: BaseCacheRequest<FromCache>
    val fromServer: BaseNetworkRequest<FromServer>
}