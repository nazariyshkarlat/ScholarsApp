package com.example.composeexample.data.use_case.articles.article_details.data_source

import com.example.composeexample.data.data_source.DataStore
import com.example.composeexample.data.use_case.articles.articles_list.entity.ArticleEntity
import com.example.composeexample.domain.feature.article.entity.Article

class ArticleDetailsDataStore : DataStore<ArticleEntity, Article, Article>()