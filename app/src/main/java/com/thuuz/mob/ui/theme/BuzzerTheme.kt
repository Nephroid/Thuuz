package com.thuuz.mob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Buzzer Ocean Blue Signature Palette
val BuzzerOceanPrimary = Color(0xFF0277BD)
val BuzzerOceanLight = Color(0xFF4FC3F7)
val BuzzerOceanDeep = Color(0xFF01579B)
val BuzzerOceanContainer = Color(0xFFE1F5FE)

val BuzzerTeal = Color(0xFF00838F)
val BuzzerTealLight = Color(0xFF4DD0E1)
val BuzzerTealContainer = Color(0xFFE0F7FA)

val BuzzerAmberAccent = Color(0xFFF9A825)
val BuzzerRedLive = Color(0xFFD32F2F)

// Excitement rating colors
val BuzzerRatingGreat = Color(0xFFFF5A4D)
val BuzzerRatingGood = Color(0xFF00ACC1)
val BuzzerRatingOk = Color(0xFFF9A825)
val BuzzerRatingDud = Color(0xFF78909C)

// Deep Navy Dark Palette
val BuzzerNavyBg = Color(0xFF06141F)
val BuzzerNavySurface = Color(0xFF0B2233)
val BuzzerNavySurfaceVariant = Color(0xFF12354D)
val BuzzerNavyOutline = Color(0xFF1F4A66)

private val BuzzerDarkColorScheme = darkColorScheme(
    primary = BuzzerOceanLight,
    onPrimary = Color(0xFF00344D),
    primaryContainer = BuzzerOceanDeep,
    onPrimaryContainer = Color(0xFFD4EEFF),
    secondary = BuzzerTealLight,
    onSecondary = Color(0xFF00363D),
    secondaryContainer = Color(0xFF004F58),
    onSecondaryContainer = Color(0xFFC8F4FA),
    tertiary = BuzzerAmberAccent,
    background = BuzzerNavyBg,
    onBackground = Color(0xFFE6F3FA),
    surface = BuzzerNavySurface,
    onSurface = Color(0xFFE6F3FA),
    surfaceVariant = BuzzerNavySurfaceVariant,
    onSurfaceVariant = Color(0xFF93B4C8),
    outline = BuzzerNavyOutline,
    outlineVariant = Color(0xFF12354D)
)

private val BuzzerLightColorScheme = lightColorScheme(
    primary = BuzzerOceanPrimary,
    onPrimary = Color.White,
    primaryContainer = BuzzerOceanContainer,
    onPrimaryContainer = BuzzerOceanDeep,
    secondary = BuzzerTeal,
    onSecondary = Color.White,
    secondaryContainer = BuzzerTealContainer,
    onSecondaryContainer = Color(0xFF004F58),
    tertiary = BuzzerAmberAccent,
    background = Color(0xFFF4F9FC),
    onBackground = Color(0xFF0F2433),
    surface = Color.White,
    onSurface = Color(0xFF0F2433),
    surfaceVariant = Color(0xFFE6F0F6),
    onSurfaceVariant = Color(0xFF486477),
    outline = Color(0xFFB7CBD8),
    outlineVariant = Color(0xFFD5E3EC)
)

enum class ThemeMode {
    SYSTEM,
    DARK,
    LIGHT
}

@Composable
fun BuzzerTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    val colorScheme = if (isDark) BuzzerDarkColorScheme else BuzzerLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
