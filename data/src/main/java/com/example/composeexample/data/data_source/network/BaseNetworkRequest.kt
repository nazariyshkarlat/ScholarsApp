package com.example.composeexample.data.data_source.network

import com.example.data.BuildConfig
import io.ktor.client.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseNetworkRequest<T: Any>: KoinComponent {

    val httpClient: HttpClient by inject()
    val baseUrl = BuildConfig.BASE_URL

    internal abstract suspend fun makeRequest(): NetworkResult<T>
}