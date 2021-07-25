package com.example.composeexample.domain.event

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.intent.ArticleDetailsScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.response.event.GetArticleEvent

sealed interface ArticleDetailsScreenEvent {
    data class ArticleDetailsReceived(val article: Article) : ArticleDetailsScreenEvent
}

fun ArticleDetailsScreenEvent.toIntent() = when(this){
    is ArticleDetailsScreenEvent.ArticleDetailsReceived -> ArticleDetailsScreenIntent.ShowArticleDetails(
        articleDetails = article
    )
    else -> Intent.DoNothing
}