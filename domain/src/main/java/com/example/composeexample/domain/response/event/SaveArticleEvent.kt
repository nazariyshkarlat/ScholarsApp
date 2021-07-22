package com.example.composeexample.domain.response.event

import com.example.composeexample.domain.result.Result

sealed interface SaveArticleEvent {
    object Success: SaveArticleEvent
}

fun Result<SaveArticleEvent>.toSaveArticleEvent(): SaveArticleEvent = when(this){
    is Result.LocalException -> ResponseEvent.Exception
    is Result.NetworkError -> ResponseEvent.NetworkUnavailable
    is Result.CacheIsEmpty -> ResponseEvent.Exception
    is Result.ServerError -> ResponseEvent.ServerError
    is Result.Success -> SaveArticleEvent.Success
}