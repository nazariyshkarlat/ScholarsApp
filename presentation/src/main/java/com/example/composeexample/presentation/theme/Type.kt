package com.example.composeexample.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

private val AppFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.product_sans_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.product_sans_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal
        )
    )
)

private val DefaultTypography = Typography()
val typography = Typography(
    h1 = DefaultTypography.h1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp,
        letterSpacing = (0.1).sp
    ),
    h2 = DefaultTypography.h2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = (0.1).sp
    ),
    h3 = DefaultTypography.h3.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = (0.1).sp
    ),
    h4 = DefaultTypography.h4.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (0.1).sp
    ),
    subtitle1 = DefaultTypography.subtitle1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.sp
    ),
    subtitle2 = DefaultTypography.subtitle2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    body1 = DefaultTypography.body1.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.sp
    ),
    body2 = DefaultTypography.body2.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),
    button = DefaultTypography.button.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 1.sp
    ),
    caption = DefaultTypography.caption.copy(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    )
)