package com.example.composeexample.presentation.extensions

import androidx.navigation.NavBackStackEntry

inline fun <reified T>NavBackStackEntry.getArgument(name: String) = this.arguments!!.get(name) as T