package com.example.composeexample.domain.event

sealed class Event : ArticlesScreenEvent, ArticleDetailsScreenEvent, ArticlesScreenClientEvent, ArticleDetailsScreenClientEvent {
    object DoNothing : Event()
}