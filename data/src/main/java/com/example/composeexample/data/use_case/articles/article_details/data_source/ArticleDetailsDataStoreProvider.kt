package com.example.composeexample.data.use_case.articles.article_details.data_source

import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.data.data_source.DataStore
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticleEntity
import com.example.composeexample.domain.feature.article.entity.Article

class ArticleDetailsDataStoreProvider(val dataStore: DataStore<ArticleEntity, Article, Article>):
    DataSourceProvider {
    override val dataSourceType: DataSourceType
        get() = DataSourceType.MEMORY
}