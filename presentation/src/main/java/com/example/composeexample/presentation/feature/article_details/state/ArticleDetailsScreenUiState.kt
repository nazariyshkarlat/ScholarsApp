package com.example.composeexample.presentation.feature.article_details.state

import com.example.composeexample.domain.state.ArticleDetailsScreenState
import com.example.composeexample.presentation.feature.article_details.entity.ArticleDetailsUi
import com.example.composeexample.presentation.feature.article_details.entity.toDetailsUi
import java.lang.UnsupportedOperationException

sealed class ArticleDetailsScreenUiState {
    data class ArticleDetails(val article: ArticleDetailsUi) : ArticleDetailsScreenUiState()
}

fun ArticleDetailsScreenState.toUi() = when(this){
    is ArticleDetailsScreenState.ArticleDetails -> ArticleDetailsScreenUiState.ArticleDetails(article = this.article.toDetailsUi())
    is ArticleDetailsScreenState.Loading -> throw UnsupportedOperationException("This UI State is unsupported!")
}
