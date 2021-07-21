package com.example.composeexample.domain.response.event

import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.Event
import com.example.composeexample.domain.feature.article.article_details.use_case.get_article.GetArticle
import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.result.Result

sealed interface GetArticleEvent {
    data class ArticleFound(val articles: Article): GetArticleEvent
    object NoArticleFound : GetArticleEvent
}

fun Result<Article>.toGetArticleEvent(): GetArticleEvent = when(this){
    is Result.LocalException -> ResponseEvent.Exception
    is Result.NetworkError -> ResponseEvent.NetworkUnavailable
    is Result.CacheIsEmpty -> GetArticleEvent.NoArticleFound
    is Result.ServerError -> ResponseEvent.ServerError
    is Result.Success -> GetArticleEvent.ArticleFound(articles = this.data)
}