package com.example.composeexample.presentation.feature.articles_list.state

import com.example.composeexample.domain.feature.articles.entity.Article
import com.example.composeexample.domain.state.ArticlesScreenState
import com.example.composeexample.domain.state.ErrorType
import com.example.composeexample.presentation.feature.articles_list.entity.ArticleUi
import com.example.composeexample.presentation.feature.articles_list.entity.toUi

sealed class ArticlesScreenUiState {
    object Loading : ArticlesScreenUiState()
    data class Error(val errorType: ErrorType) : ArticlesScreenUiState()
    object EmptyArticlesScreen : ArticlesScreenUiState()
    data class ArticlesListScreen(val articles: List<ArticleUi>) : ArticlesScreenUiState()
}

fun ArticlesScreenState.toUi() = when(this){
    is ArticlesScreenState.ArticlesListScreen -> ArticlesScreenUiState.ArticlesListScreen(articles.map{ it.toUi() })
    ArticlesScreenState.EmptyArticlesScreen -> ArticlesScreenUiState.EmptyArticlesScreen
    is ArticlesScreenState.Error -> ArticlesScreenUiState.Error(errorType = errorType)
    ArticlesScreenState.Loading -> ArticlesScreenUiState.Loading
}