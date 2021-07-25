package com.example.composeexample.domain.intent

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.state.ArticleDetailsScreenState

sealed interface ArticleDetailsScreenIntent {
    data class ShowArticleDetails(val articleDetails: Article) : ArticleDetailsScreenIntent
}