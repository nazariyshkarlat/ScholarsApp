package com.example.composeexample.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.composeexample.presentation.extensions.AppColors

private val LocalColors = staticCompositionLocalOf {
    LightColorPalette
}

private val LightColorPalette = AppColors(
    material = lightColors(
        primary = blue,
        secondary = blue_light,
        background = white,
        surface = black8,
        onPrimary = white,
        onSecondary = white,
        onBackground = black,
        onSurface = blue
    ),
    onBackgroundSecondary = black80,
    onBackgroundTertiary = black50,
)

private val DarkColorPalette = AppColors(
    material = darkColors(
        primary = blue,
        secondary = blue_light,
        background = black,
        surface = white12,
        onPrimary = white,
        onSecondary = white,
        onBackground = white,
        onSurface = blue
    ),
    onBackgroundSecondary = white80,
    onBackgroundTertiary = white50,
)

val MaterialTheme.appColors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}
