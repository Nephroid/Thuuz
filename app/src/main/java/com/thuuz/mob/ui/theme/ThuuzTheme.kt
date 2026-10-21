package com.thuuz.mob.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Thuuz Sports Signature Palette
val ThuuzGreenPrimary = Color(0xFF2E7D32)
val ThuuzGreenDark = Color(0xFF1B5E20)
val ThuuzGreenLight = Color(0xFF4CAF50)
val ThuuzGreenContainer = Color(0xFFE8F5E9)

val ThuuzBlueElectric = Color(0xFF1565C0)
val ThuuzBlueLight = Color(0xFF42A5F5)
val ThuuzBlueContainer = Color(0xFFE3F2FD)

val ThuuzAmberAccent = Color(0xFFFF8F00)
val ThuuzRedLive = Color(0xFFD32F2F)

// Stadium Dark Palette
val StadiumDarkBg = Color(0xFF0D1117)
val StadiumDarkSurface = Color(0xFF161B22)
val StadiumDarkSurfaceVariant = Color(0xFF21262D)
val StadiumDarkOutline = Color(0xFF30363D)

private val ThuuzDarkColorScheme = darkColorScheme(
    primary = ThuuzGreenLight,
    onPrimary = Color(0xFF00390F),
    primaryContainer = ThuuzGreenDark,
    onPrimaryContainer = Color(0xFFB7F397),
    secondary = ThuuzBlueLight,
    onSecondary = Color(0xFF003258),
    secondaryContainer = Color(0xFF0D47A1),
    onSecondaryContainer = Color(0xFFD1E4FF),
    tertiary = ThuuzAmberAccent,
    background = StadiumDarkBg,
    onBackground = Color(0xFFF0F6FC),
    surface = StadiumDarkSurface,
    onSurface = Color(0xFFF0F6FC),
    surfaceVariant = StadiumDarkSurfaceVariant,
    onSurfaceVariant = Color(0xFF8B949E),
    outline = StadiumDarkOutline,
    outlineVariant = Color(0xFF21262D)
)

private val ThuuzLightColorScheme = lightColorScheme(
    primary = ThuuzGreenPrimary,
    onPrimary = Color.White,
    primaryContainer = ThuuzGreenContainer,
    onPrimaryContainer = ThuuzGreenDark,
    secondary = ThuuzBlueElectric,
    onSecondary = Color.White,
    secondaryContainer = ThuuzBlueContainer,
    onSecondaryContainer = Color(0xFF0D47A1),
    tertiary = ThuuzAmberAccent,
    background = Color(0xFFF8F9FA),
    onBackground = Color(0xFF1F2328),
    surface = Color.White,
    onSurface = Color(0xFF1F2328),
    surfaceVariant = Color(0xFFF0F2F5),
    onSurfaceVariant = Color(0xFF57606A),
    outline = Color(0xFFD0D7DE),
    outlineVariant = Color(0xFFE1E4E8)
)

enum class ThemeMode {
    SYSTEM,
    DARK,
    LIGHT
}

@Composable
fun ThuuzTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.DARK -> true
        ThemeMode.LIGHT -> false
    }

    val colorScheme = if (isDark) ThuuzDarkColorScheme else ThuuzLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
