package com.example.composeexample.domain.response.event

import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.Event
import com.example.composeexample.domain.feature.articles.entity.Article

sealed interface GetArticlesEvent {
    object NoArticlesFound: GetArticlesEvent
    data class ArticlesFound(val articles: List<Article>): GetArticlesEvent
    object ServerError: GetArticlesEvent
}

fun GetArticlesEvent.toArticlesScreenEvent() = when(this){
    is GetArticlesEvent.ArticlesFound -> ArticlesScreenEvent.ArticlesFound(articles = this.articles)
    GetArticlesEvent.NoArticlesFound -> ArticlesScreenEvent.ArticlesNotFound
    ResponseEvent.DoNothing -> Event.DoNothing
    ResponseEvent.Exception -> ArticlesScreenEvent.GetArticlesException
    ResponseEvent.Loading -> ArticlesScreenEvent.GetArticlesLoading
    ResponseEvent.NetworkUnavailable -> ArticlesScreenEvent.GetArticlesNetworkError
    GetArticlesEvent.ServerError -> ArticlesScreenEvent.GetArticlesServerError
}