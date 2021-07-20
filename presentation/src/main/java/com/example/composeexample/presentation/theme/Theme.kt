package com.example.composeexample.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = blue,
    secondary = grayish_blue_300,
    background = blue,
    surface = white_50_65a,
    onPrimary = white_50,
    onSecondary = white_50,
    onBackground = white_50,
    onSurface = blue
)

private val LightColorPalette = lightColors(
    primary = blue,
    secondary = grayish_blue_300,
    background = blue,
    surface = white_50_65a,
    onPrimary = white_50,
    onSecondary = white_50,
    onBackground = white_50,
    onSurface = blue
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
