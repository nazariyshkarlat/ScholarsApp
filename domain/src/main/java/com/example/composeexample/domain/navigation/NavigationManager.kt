package com.example.composeexample.domain.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.receiveAsFlow

object NavigationManager {

    private val _command = Channel<NavigationCommand.CommandResult>(Channel.BUFFERED)
    val command = _command.receiveAsFlow()

    fun navigate(directions: NavigationCommand.CommandResult) {
        _command.trySend(directions)
    }

    fun navigateBack() {
        _command.trySend(NavigationCommand.CommandResult.navigateBack)
    }
}
