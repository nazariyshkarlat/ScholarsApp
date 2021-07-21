package com.example.composeexample.domain.feature.article.repository

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.result.Result

interface ArticlesRepository {
    suspend fun getArticles() : Result<List<Article>>

    suspend fun getArticle(articleId: String) : Result<Article>

    suspend fun saveArticle(article: Article)
}