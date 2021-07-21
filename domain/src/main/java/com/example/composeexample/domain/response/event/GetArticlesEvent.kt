package com.example.composeexample.domain.response.event

import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.Event
import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.result.Result

sealed interface GetArticlesEvent {
    object NoArticlesFound: GetArticlesEvent
    data class ArticlesFound(val articles: List<Article>): GetArticlesEvent
}

fun GetArticlesEvent.toArticlesScreenEvent() = when(this){
    is GetArticlesEvent.ArticlesFound -> ArticlesScreenEvent.ArticlesFound(articles = this.articles)
    GetArticlesEvent.NoArticlesFound -> ArticlesScreenEvent.ArticlesNotFound
    ResponseEvent.DoNothing -> Event.DoNothing
    ResponseEvent.Exception -> ArticlesScreenEvent.GetArticlesException
    ResponseEvent.Loading -> ArticlesScreenEvent.GetArticlesLoading
    ResponseEvent.NetworkUnavailable -> ArticlesScreenEvent.GetArticlesNetworkError
    ResponseEvent.ServerError -> ArticlesScreenEvent.GetArticlesServerError
}

fun Result<List<Article>>.toGetArticlesEvent() = when(this){
    is Result.LocalException -> ResponseEvent.Exception
    is Result.NetworkError -> ResponseEvent.NetworkUnavailable
    is Result.CacheIsEmpty -> GetArticlesEvent.NoArticlesFound
    is Result.ServerError -> ResponseEvent.ServerError
    is Result.Success -> GetArticlesEvent.ArticlesFound(articles = this.data)
}