package com.example.composeexample.domain.feature.article.article_details.use_case.get_article

import com.example.composeexample.domain.feature.article.repository.ArticlesRepository
import com.example.composeexample.domain.response.event.GetArticleEvent
import com.example.composeexample.domain.response.event.ResponseEvent
import com.example.composeexample.domain.response.event.toGetArticleEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticleImpl(private val articlesRepository: ArticlesRepository) : GetArticle{
    override suspend fun getArticle(articleId: String) = flow{
        emit(ResponseEvent.Loading)
        emit(articlesRepository.getArticle(articleId).toGetArticleEvent())
    }

}

interface GetArticle {
    suspend fun getArticle(articleId: String) : Flow<GetArticleEvent>
}