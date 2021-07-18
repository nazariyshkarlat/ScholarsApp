package com.example.composeexample.data.use_case.get_articles.repository

import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.data.data_source.api.toResult
import com.example.composeexample.data.data_source.cache.toResult
import com.example.composeexample.data.use_case.get_articles.data_source.GetArticlesDataSource
import com.example.composeexample.data.use_case.get_articles.entity.toArticles
import com.example.composeexample.domain.feature.articles.entity.Article
import com.example.composeexample.domain.feature.articles.repository.ArticlesRepository
import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.domain.result.Result

class ArticlesRepositoryImpl(
    private val dataSourceProvider: DataSourceProvider,
    private val articlesDataSource: GetArticlesDataSource): ArticlesRepository {
    override suspend fun getArticles(): Result<List<Article>> =
        when(dataSourceProvider.dataSourceType){
            DataSourceType.CACHE -> articlesDataSource.fromCache.makeRequest().toResult {
                it.toArticles()
            }
            DataSourceType.NETWORK -> articlesDataSource.fromServer.makeRequest().toResult {
                it.toArticles()
            }
        }


}
