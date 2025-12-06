package com.example.sereniview.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

// DARK MODE COLORS
private val DarkColors = darkColorScheme(
    primary = SereniVioletSoft,
    onPrimary = Color.White,
    secondary = SereniRose,
    onSecondary = Color.White,
    background = SereniBurgundyDark,
    onBackground = Color.White,          // text on background = white
    surface = SereniCardDark,
    onSurface = Color.White,             // text on cards = white
)

// LIGHT MODE COLORS (not really used with your gradient, but kept)
private val LightColors = lightColorScheme(
    primary = SereniBurgundy,
    onPrimary = Color.White,
    secondary = SereniViolet,
    onSecondary = Color.White,
    background = SereniMist,
    onBackground = Color.Black,
    surface = SereniCardLight,
    onSurface = Color.Black,
)

@Composable
fun SereniviewTheme(
    darkTheme: Boolean = true,   // force dark theme for your design
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    // Make default text/content color follow onBackground (white in dark mode)
    CompositionLocalProvider(
        LocalContentColor provides colors.onBackground
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}