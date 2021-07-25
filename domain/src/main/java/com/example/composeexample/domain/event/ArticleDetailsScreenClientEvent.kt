package com.example.composeexample.domain.event

import com.example.composeexample.domain.intent.ArticleDetailsScreenIntent
import com.example.composeexample.domain.intent.ArticlesScreenIntent
import com.example.composeexample.domain.mvi.eventHandler
import com.example.composeexample.domain.state.ArticleDetailsScreenState
import com.example.composeexample.domain.state.ArticlesScreenState

sealed interface ArticleDetailsScreenClientEvent {
}

fun articleDetailsScreenEventHandler() =
    eventHandler<ArticleDetailsScreenState, ArticleDetailsScreenIntent, ArticleDetailsScreenClientEvent>{ currentState, intent->
        Event.DoNothing
    }