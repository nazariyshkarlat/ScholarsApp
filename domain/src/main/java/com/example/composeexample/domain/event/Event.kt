package com.example.composeexample.domain.event

sealed class Event : ArticlesScreenEvent, ArticlesScreenClientEvent {
    object DoNothing : Event()
}