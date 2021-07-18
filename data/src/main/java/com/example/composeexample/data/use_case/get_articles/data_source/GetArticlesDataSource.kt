package com.example.composeexample.data.use_case.get_articles.data_source

import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.api.BaseNetworkRequest
import com.example.composeexample.data.use_case.get_articles.data_source.api.GetArticlesNetworkRequest
import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.use_case.get_articles.data_source.cache.GetArticlesCacheRequest
import com.example.composeexample.data.use_case.get_articles.entity.ArticleEntity
import com.example.composeexample.data.use_case.get_articles.entity.ArticlesResponse
import java.lang.UnsupportedOperationException

class GetArticlesDataSource(
    private val networkRequest: GetArticlesNetworkRequest,
    private val cacheRequest: GetArticlesCacheRequest) : DataSource<ArticlesResponse, List<ArticleEntity>> {

    override val fromCache: BaseCacheRequest<List<ArticleEntity>>
        get() {
            throw UnsupportedOperationException("Cache is unsupported here")
        }
    override val fromServer: BaseNetworkRequest<ArticlesResponse> = networkRequest

}