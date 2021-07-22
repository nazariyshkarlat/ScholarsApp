package com.example.composeexample.data.use_case.articles.articles_list.data_source

import com.example.composeexample.data.data_source.DataSource
import com.example.composeexample.data.data_source.DataSourceProvider
import com.example.composeexample.domain.result.DataSourceType
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticleEntity
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticlesResponse

class ArticlesDataSourceProvider(val dataSource: DataSource<ArticlesResponse, List<ArticleEntity>, List<ArticleEntity>>): DataSourceProvider {
    override val dataSourceType: DataSourceType
        get() = DataSourceType.Network
}