package com.example.composeexample.domain.intent

import com.example.composeexample.domain.feature.article.entity.Article

sealed interface ArticlesScreenIntent {
    object ShowLoadingScreen : ArticlesScreenIntent
    object ShowNetworkError : ArticlesScreenIntent
    object ShowExceptionScreen : ArticlesScreenIntent
    object ShowServerError : ArticlesScreenIntent
    object ShowEmptyScreen : ArticlesScreenIntent
    data class ShowArticlesList(val articles: List<Article>) : ArticlesScreenIntent
    data class ShowArticleScreen(val article: Article) : ArticlesScreenIntent
}