package com.example.composeexample.domain.navigation

import kotlinx.coroutines.flow.MutableStateFlow

object NavigationManager {

    val command = MutableStateFlow(NavigationCommand.Default.create())

    fun navigate(directions: NavigationCommand.CommandResult) {
        command.value = directions
    }
}