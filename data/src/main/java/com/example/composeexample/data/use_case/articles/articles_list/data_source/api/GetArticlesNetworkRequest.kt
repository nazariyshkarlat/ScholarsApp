package com.example.composeexample.data.use_case.articles.articles_list.data_source.api

import com.example.composeexample.data.data_source.network.BaseNetworkRequest
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticlesResponse
import com.example.composeexample.data.extensions.handleNetworkRequest
import io.ktor.client.request.*


class GetArticlesNetworkRequest :
    BaseNetworkRequest<ArticlesResponse>() {

    override suspend fun makeRequest() =
        suspend {
            httpClient.use {
                it.get<ArticlesResponse>(baseUrl) {
                    parameter("search_query", "cat:astro-ph")
                }
            }
        }.handleNetworkRequest()

}