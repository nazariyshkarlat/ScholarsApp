package com.example.composeexample.domain.response.event

sealed class ResponseEvent : GetArticlesEvent {
    object Loading: ResponseEvent()
    object DoNothing: ResponseEvent()
    object NetworkUnavailable: ResponseEvent()
    object Exception: ResponseEvent()
}