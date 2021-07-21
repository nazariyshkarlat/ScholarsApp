package com.example.composeexample.data.use_case.articles.articles_list.data_source.cache

import com.example.composeexample.data.data_source.cache.BaseCacheRequest
import com.example.composeexample.data.data_source.cache.CacheResult
import com.example.composeexample.domain.feature.article.entity.Article

class GetArticlesCacheRequest : BaseCacheRequest<List<Article>> {
    override suspend fun makeRequest(): CacheResult<List<Article>> {
        TODO("Not yet implemented")
    }
}