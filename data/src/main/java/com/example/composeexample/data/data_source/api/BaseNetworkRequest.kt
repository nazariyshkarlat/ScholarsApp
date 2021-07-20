package com.example.composeexample.data.data_source.api

interface BaseNetworkRequest<T: Any> {

    suspend fun makeRequest(): ServerResult<T>
}