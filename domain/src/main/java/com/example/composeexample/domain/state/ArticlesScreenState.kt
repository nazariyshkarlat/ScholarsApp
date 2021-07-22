package com.example.composeexample.domain.state

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.feature.article.entity.Category
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.mvi.reducer

sealed class ArticlesScreenState {
    abstract val category: Category
    data class Loading(override val category: Category) : ArticlesScreenState()
    data class Error(val errorType: ErrorType, override val category: Category) : ArticlesScreenState()
    data class EmptyArticlesScreen(override val category: Category) : ArticlesScreenState()
    data class ArticlesListScreen(val articles: List<Article>, override val category: Category) : ArticlesScreenState()
}

enum class ErrorType{
    ServerError, NetworkError, Exception
}

fun articlesScreenReducer() = reducer<ArticlesScreenState, ArticlesScreenIntent> { currentState, intent ->
    when(intent){
        Intent.DoNothing -> currentState
        is ArticlesScreenIntent.ShowArticlesList -> {
            ArticlesScreenState.ArticlesListScreen(articles = intent.articles, category = currentState.category)
        }
        ArticlesScreenIntent.ShowEmptyScreen -> {
            ArticlesScreenState.EmptyArticlesScreen(category = currentState.category)
        }
        ArticlesScreenIntent.ShowLoadingScreen -> {
            ArticlesScreenState.Loading(category = currentState.category)
        }
        ArticlesScreenIntent.ShowNetworkError -> {
            ArticlesScreenState.Error(errorType = ErrorType.NetworkError, category = currentState.category)
        }
        ArticlesScreenIntent.ShowServerError -> {
            ArticlesScreenState.Error(errorType = ErrorType.ServerError, category = currentState.category)
        }
        ArticlesScreenIntent.ShowExceptionScreen -> {
            ArticlesScreenState.Error(errorType = ErrorType.Exception, category = currentState.category)
        }
        is ArticlesScreenIntent.ShowArticleScreen -> currentState
    }
}