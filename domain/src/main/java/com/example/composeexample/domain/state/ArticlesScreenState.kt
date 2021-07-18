package com.example.composeexample.domain.state

import com.example.composeexample.domain.feature.articles.entity.Article
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.mvi.reducer

sealed class ArticlesScreenState {
    object Loading : ArticlesScreenState()
    data class Error(val errorType: ErrorType) : ArticlesScreenState()
    object EmptyArticlesScreen : ArticlesScreenState()
    data class ArticlesListScreen(val articles: List<Article>) : ArticlesScreenState()
}

enum class ErrorType{
    SERVER_ERROR, NETWORK_ERROR, EXCEPTION
}

fun articlesScreenReducer() = reducer<ArticlesScreenState, ArticlesScreenIntent> { currentState, intent ->
    when(intent){
        Intent.DoNothing -> currentState
        is ArticlesScreenIntent.ShowArticlesList -> {
            ArticlesScreenState.ArticlesListScreen(articles = intent.articles)
        }
        ArticlesScreenIntent.ShowEmptyScreen -> {
            ArticlesScreenState.EmptyArticlesScreen
        }
        ArticlesScreenIntent.ShowLoadingScreen -> {
            ArticlesScreenState.Loading
        }
        ArticlesScreenIntent.ShowNetworkError -> {
            ArticlesScreenState.Error(errorType = ErrorType.NETWORK_ERROR)
        }
        ArticlesScreenIntent.ShowServerError -> {
            ArticlesScreenState.Error(errorType = ErrorType.SERVER_ERROR)
        }
        ArticlesScreenIntent.ShowExceptionScreen -> {
            ArticlesScreenState.Error(errorType = ErrorType.EXCEPTION)
        }
        is ArticlesScreenIntent.ShowArticleScreen -> currentState
    }
}