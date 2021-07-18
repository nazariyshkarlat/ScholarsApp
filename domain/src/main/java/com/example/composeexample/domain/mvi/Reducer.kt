package com.example.composeexample.domain.mvi

import com.example.composeexample.domain.intent.Intent

typealias Reducer<Intent, State> = State.(intent: Intent) -> State

inline fun <reified State: Any, reified Intent>
        reducer(crossinline block: (State, Intent) -> State): Reducer<Intent, State> =
    { intent -> block(this, intent) }
