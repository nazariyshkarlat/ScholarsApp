package com.example.composeexample.domain.feature.article.article_details.use_case.save_article

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import com.example.composeexample.domain.response.event.GetArticleEvent
import com.example.composeexample.domain.response.event.ResponseEvent
import com.example.composeexample.domain.response.event.SaveArticleEvent
import com.example.composeexample.domain.response.event.toGetArticleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveArticleImpl(private val articlesRepository: ArticlesRepository) : SaveArticle{

    override suspend fun saveArticle(article: Article){
        articlesRepository.saveArticle(article)
    }
}

interface SaveArticle {
    suspend fun saveArticle(article: Article)
}