package com.example.composeexample.domain.event

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.mvi.eventHandler
import com.example.composeexample.domain.state.ArticlesScreenState

sealed interface ArticlesScreenEvent {
    object GetArticlesServerError : ArticlesScreenEvent
    object GetArticlesNetworkError : ArticlesScreenEvent
    object GetArticlesException : ArticlesScreenEvent
    object GetArticlesLoading : ArticlesScreenEvent
    object ArticlesNotFound : ArticlesScreenEvent
    data class ArticlesFound(val articles: List<Article>) : ArticlesScreenEvent
}

fun ArticlesScreenEvent.toIntent(): ArticlesScreenIntent = when(this){
    is ArticlesScreenEvent.ArticlesFound -> ArticlesScreenIntent.ShowArticlesList(articles = articles)
    ArticlesScreenEvent.ArticlesNotFound -> ArticlesScreenIntent.ShowEmptyScreen
    ArticlesScreenEvent.GetArticlesException -> ArticlesScreenIntent.ShowExceptionScreen
    ArticlesScreenEvent.GetArticlesLoading -> ArticlesScreenIntent.ShowLoadingScreen
    ArticlesScreenEvent.GetArticlesNetworkError -> ArticlesScreenIntent.ShowNetworkError
    ArticlesScreenEvent.GetArticlesServerError -> ArticlesScreenIntent.ShowServerError
    else -> Intent.DoNothing
}