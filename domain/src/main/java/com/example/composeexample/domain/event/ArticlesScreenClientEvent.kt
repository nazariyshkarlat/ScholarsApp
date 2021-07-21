package com.example.composeexample.domain.event

import com.example.composeexample.domain.feature.article.entity.Article
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.mvi.eventHandler
import com.example.composeexample.domain.state.ArticlesScreenState

sealed interface ArticlesScreenClientEvent {
    data class NavigateToArticleScreen(val article: Article) : ArticlesScreenClientEvent
}

fun articlesScreenEventHandler() =
    eventHandler<ArticlesScreenState, ArticlesScreenIntent, ArticlesScreenClientEvent>{ currentState, intent->
        when(intent){
            is ArticlesScreenIntent.ShowArticleScreen -> ArticlesScreenClientEvent.NavigateToArticleScreen(article = intent.article)
            else -> Event.DoNothing
        }
    }