package com.example.composeexample.domain.feature.articles.repository

import com.example.composeexample.domain.feature.articles.entity.Article
import com.example.composeexample.domain.result.Result

interface ArticlesRepository {
    suspend fun getArticles() : Result<List<Article>>
}