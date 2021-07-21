package com.example.composeexample.domain.response.event

sealed class ResponseEvent : GetArticlesEvent, GetArticleEvent, SaveArticleEvent {
    object Loading: ResponseEvent()
    object DoNothing: ResponseEvent()
    object ServerError: ResponseEvent()
    object NetworkUnavailable: ResponseEvent()
    object Exception: ResponseEvent()
}