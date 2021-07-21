package com.example.composeexample.presentation.feature.articles_list.state

import com.example.composeexample.domain.entity.Category
import com.example.composeexample.domain.state.ArticlesScreenState
import com.example.composeexample.domain.state.ErrorType
import com.example.composeexample.presentation.feature.articles_list.entity.ArticleListItemUi
import com.example.composeexample.presentation.feature.articles_list.entity.toListItemUi
import com.example.composeexample.presentation.feature.articles_list.entity.toUi

sealed class ArticlesScreenUiState {
    abstract val category: String
    data class Loading(override val category: String) : ArticlesScreenUiState()
    data class Error(val errorType: ErrorType, override val category: String) : ArticlesScreenUiState()
    data class EmptyArticlesScreen(override val category: String) : ArticlesScreenUiState()
    data class ArticlesListScreen(val articles: List<ArticleListItemUi>, override val category: String) : ArticlesScreenUiState()
}

fun ArticlesScreenState.toUi() = when(this){
    is ArticlesScreenState.ArticlesListScreen -> ArticlesScreenUiState.ArticlesListScreen(articles.map{ it.toListItemUi() }, category = category.toUi())
    is ArticlesScreenState.EmptyArticlesScreen -> ArticlesScreenUiState.EmptyArticlesScreen(category = category.toUi())
    is ArticlesScreenState.Error -> ArticlesScreenUiState.Error(errorType = errorType, category = category.toUi())
    is ArticlesScreenState.Loading -> ArticlesScreenUiState.Loading(category = category.toUi())
}