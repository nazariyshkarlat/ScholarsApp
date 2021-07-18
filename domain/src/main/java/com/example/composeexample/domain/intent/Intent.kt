package com.example.composeexample.domain.intent

import com.example.composeexample.domain.event.ArticlesScreenEvent

sealed class Intent : ArticlesScreenIntent {
    object DoNothing: Intent()
}