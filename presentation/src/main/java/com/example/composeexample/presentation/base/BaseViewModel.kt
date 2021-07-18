package com.example.composeexample.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, UiState, Intent, ClientEvent, ClientAction, InternalEvent> : ViewModel() {

    protected abstract val state: MutableStateFlow<State>
    protected abstract val uiState: StateFlow<UiState>

    protected abstract val _events: Channel<ClientEvent>
    val events by lazy { _events.receiveAsFlow() }

    abstract fun consumeClientAction(action: ClientAction)

    protected abstract fun consumeInternalEvent(internalEvent: InternalEvent)

    protected abstract fun consumeIntent(intent: Intent)

}