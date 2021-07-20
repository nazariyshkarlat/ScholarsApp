package com.example.composeexample.domain.action

import com.example.composeexample.domain.event.ArticlesScreenEvent
import com.example.composeexample.domain.event.Event
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.intent.Intent
import com.example.composeexample.domain.state.ArticlesScreenState

sealed class ArticlesScreenClientAction {
    data class ArticleClick(val articleId: String) : ArticlesScreenClientAction()
}

fun ArticlesScreenClientAction.toArticlesScreenIntent(state: ArticlesScreenState) = when(this){
    is ArticlesScreenClientAction.ArticleClick -> {
        when(state){
            is ArticlesScreenState.ArticlesListScreen -> {
              ArticlesScreenIntent.ShowArticleScreen(
                  article = state.articles.first { it.id == articleId }
              )
            }
            else -> Intent.DoNothing
        }
    }
}