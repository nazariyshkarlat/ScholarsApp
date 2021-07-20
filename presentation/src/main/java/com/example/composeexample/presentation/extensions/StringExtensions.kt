package com.example.composeexample.presentation.extensions

import com.example.composeexample.core.application
import java.text.SimpleDateFormat
import java.util.*

private val uiDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)


val Int.str: String
    get() = application.getString(this)

fun Int.str(vararg arguments: Any) = application.getString(this, *arguments)

val Date.formattedDate
get() = uiDateFormat.format(this)