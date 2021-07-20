package com.example.composeexample.presentation.navigation

import kotlinx.coroutines.flow.MutableStateFlow

object NavigationManager {

    val command = MutableStateFlow(NavigationCommand.Default)

    fun navigate(directions: NavigationCommand) {
        command.value = directions
    }
}
