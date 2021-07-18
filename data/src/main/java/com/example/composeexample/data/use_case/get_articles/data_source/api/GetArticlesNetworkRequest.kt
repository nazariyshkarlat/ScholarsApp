package com.example.composeexample.data.use_case.get_articles.data_source.api

import com.example.composeexample.data.data_source.api.BaseNetworkRequest
import com.example.composeexample.data.use_case.get_articles.entity.ArticlesResponse
import com.example.composeexample.data.extensions.handleNetworkRequest
import io.ktor.client.*
import io.ktor.client.request.*

class GetArticlesNetworkRequest(private val baseUrl: String, private val client: HttpClient) :
    BaseNetworkRequest<ArticlesResponse> {

    override suspend fun makeRequest() =
        suspend {
            client.use {
                it.get<ArticlesResponse>(baseUrl) {
                    parameter("search_query", "cat:astro-ph")
                }
            }
        }.handleNetworkRequest()

}