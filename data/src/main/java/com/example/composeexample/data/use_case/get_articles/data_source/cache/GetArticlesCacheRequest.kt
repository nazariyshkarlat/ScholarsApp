package com.example.composeexample.data.use_case.get_articles.data_source.cache

import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.data_source.cache.CacheResult
import com.example.composeexample.data.use_case.get_articles.entity.ArticlesResponse

class GetArticlesCacheRequest : BaseCacheRequest<List<ArticlesResponse>> {
    override suspend fun makeRequest(): CacheResult<List<ArticlesResponse>> {
        TODO("Not yet implemented")
    }
}