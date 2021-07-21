package com.example.composeexample.data.use_case.articles

import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.data.data_source.DataStore
import com.example.composeexample.data.data_source.cache.toResult
import com.example.composeexample.data.data_source.memory.toResult
import com.example.composeexample.data.data_source.network.toResult
import com.example.composeexample.data.use_case.articles.article_details.data_source.memory.GetArticleDetailsMemoryRequest
import com.example.composeexample.data.use_case.articles.article_details.data_source.memory.SaveArticleDetailsMemoryRequest
import com.example.composeexample.data.use_case.articles.articles_list.data_source.api.GetArticlesNetworkRequest
import com.example.composeexample.data.use_case.articles.articles_list.data_source.cache.GetArticlesCacheRequest
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticleEntity
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticlesResponse
import com.example.composeexample.data.use_case.articles.articles_list.entity.toArticles
import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import com.example.composeexample.domain.result.Result
import java.lang.UnsupportedOperationException

class ArticlesRepositoryImpl(
    private val articlesDataSourceProvider: DataSourceProvider,
    private val articleDetailsDataSourceProvider: DataSourceProvider,
    private val articleDetailsDataStoreProvider: DataSourceProvider,
    private val articlesDataSource: DataSource<ArticlesResponse, List<Article>, Article>,
    private val articleDetailsDataSource: DataSource<ArticleEntity, Article, Article>,
    private val articleDetailsDataStore: DataStore<ArticleEntity, Article, Article>
    ): ArticlesRepository {
    override suspend fun getArticles(): Result<List<Article>> =
        when(articlesDataSourceProvider.dataSourceType){
            DataSourceType.CACHE -> articlesDataSource.getFromCache(
                GetArticlesCacheRequest()
            ).toResult {
                it
            }
            DataSourceType.NETWORK -> articlesDataSource.getFromServer(
                GetArticlesNetworkRequest()
            ).toResult {
                it.toArticles()
            }
            DataSourceType.MEMORY -> {
                throw UnsupportedOperationException()
            }
        }


    override suspend fun getArticle(articleId: String): Result<Article> =
        when(articleDetailsDataSourceProvider.dataSourceType){
           DataSourceType.MEMORY -> {
               articleDetailsDataSource.getFromMemory(
                   GetArticleDetailsMemoryRequest(
                       articleId = articleId
                   )
               ).toResult {
                   it
               }
           }
            else -> {
                throw UnsupportedOperationException()
            }
        }

    override suspend fun saveArticle(article: Article) {
        when (articleDetailsDataStoreProvider.dataSourceType) {
            DataSourceType.MEMORY -> {
                articleDetailsDataStore.saveToMemory(
                    SaveArticleDetailsMemoryRequest(
                        article = article
                    )
                ).toResult {
                    it
                }
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }

}
