package com.example.composeexample.domain.response.event

import com.example.composeexample.domain.event.ArticleDetailsScreenEvent
import com.example.composeexample.domain.event.Event
import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.result.Result

sealed interface GetArticleEvent {
    data class ArticleFound(val article: Article): GetArticleEvent
    object NoArticleFound : GetArticleEvent
}

fun Result<Article>.toGetArticleEvent(): GetArticleEvent = when(this){
    is Result.LocalException -> ResponseEvent.Exception
    is Result.NetworkError -> ResponseEvent.NetworkUnavailable
    is Result.CacheIsEmpty -> GetArticleEvent.NoArticleFound
    is Result.ServerError -> ResponseEvent.ServerError
    is Result.Success -> GetArticleEvent.ArticleFound(article = this.data)
}

fun GetArticleEvent.toArticleDetailsScreenEvent() = when(this){
    is GetArticleEvent.ArticleFound -> ArticleDetailsScreenEvent.ArticleDetailsReceived(article = this.article)
    else -> Event.DoNothing
}