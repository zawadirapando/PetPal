package com.example.petpal.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class PetPalExtraColors(
    //field background
    val fieldFill: Color,
    val fieldFillFocused: Color,

    //field boarder
    val fieldBorderInactive: Color,
    val fieldBorderFocused: Color,
    val fieldBorderError: Color,

    //dividers
    val divider: Color,

    //finer text levels
    val textSecondary: Color,
    val textLabel: Color,
    val textHint: Color,

    //status fills
    val statusGreenFill: Color,
    val statusGreenText: Color,
    val statusAmberFill: Color,
    val statusAmberText: Color,
    val statusRedFill: Color,
    val statusRedText: Color,

    //rescue view pink fill
    val pinkFill: Color,

    //blue accents
    val blueFill: Color,
)

val LocalPetPalColors = staticCompositionLocalOf<PetPalExtraColors> {
    error("LocalPetPalColors not provided. Wrap your app in PetPalTheme.")
}

//Dark theme
private val DarkColorScheme = darkColorScheme(
    primary = BlueDark,
    onPrimary = White,
    primaryContainer = BlueDimDark,
    onPrimaryContainer = Blue,

    secondary = Pink,
    onSecondary = DarkBackground,
    secondaryContainer = PinkDimDark,
    onSecondaryContainer = Pink,

    tertiary = White,
    onTertiary = DarkBackground,
    tertiaryContainer = DarkSurface3,
    onTertiaryContainer = TextDarkPrimary,

    background = DarkBackground,
    onBackground = TextDarkPrimary,

    surface = DarkSurface,
    onSurface = TextDarkPrimary,

    surfaceVariant = DarkSurface2,
    onSurfaceVariant = TextDarkLabel,

    outline = FieldFillDarkFocused,
    outlineVariant = FieldBorderInactiveDark,

    error = StatusRed,
    onError = White,
    errorContainer = Color(0xFF3B0A0A),
    onErrorContainer = Color(0xFFFFB4AB),

    scrim = Color(0x99000000),
)

//Light theme
private val LightColorScheme = lightColorScheme(
    primary             = BlueDark,
    onPrimary           = White,
    primaryContainer    = BlueMuted,
    onPrimaryContainer  = BlueDark,

    secondary           = PinkDark,
    onSecondary         = White,
    secondaryContainer  = PinkMuted,
    onSecondaryContainer = PinkDark,

    tertiary            = BlueDark,
    onTertiary          = White,
    tertiaryContainer   = LightSurface3,
    onTertiaryContainer = TextLightPrimary,

    background          = LightBackground,
    onBackground        = TextLightPrimary,

    surface             = LightSurface,
    onSurface           = TextLightPrimary,
    surfaceVariant      = LightSurface2,
    onSurfaceVariant    = TextLightLabel,

    outline             = FieldBorderFocused,
    outlineVariant      = FieldBorderInactive,

    error               = StatusRed,
    onError             = White,
    errorContainer      = Color(0xFFFFDAD6),
    onErrorContainer    = Color(0xFF410002),

    scrim               = Color(0x66000000),
)

@Composable
fun PetPalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val extraColors = if (darkTheme) {
        PetPalExtraColors(
            fieldFill = FieldFillDark,
            fieldFillFocused = FieldFillDarkFocused,
            fieldBorderInactive = FieldBorderInactiveDark,
            fieldBorderFocused = FieldBorderFocused,
            fieldBorderError = FieldBorderError,
            divider = DividerDark,
            textSecondary = TextDarkSecondary,
            textLabel = TextDarkLabel,
            textHint = TextDarkHint,
            statusGreenFill = StatusGreenDark,
            statusGreenText = StatusGreen,
            statusAmberFill = StatusAmberDark,
            statusAmberText = StatusAmber,
            statusRedFill = StatusRedDark,
            statusRedText = StatusRed,
            pinkFill = PinkDimDark,
            blueFill = BlueDimDark
        )
    } else {
        PetPalExtraColors(
            fieldFill = FieldFillLight,
            fieldFillFocused = FieldFillLightFocused,
            fieldBorderInactive = FieldBorderInactive,
            fieldBorderFocused = FieldBorderFocused,
            fieldBorderError = FieldBorderError,
            divider = DividerLight,
            textSecondary = TextLightSecondary,
            textLabel = TextLightLabel,
            textHint = TextLightHint,
            statusGreenFill = StatusGreenMuted,
            statusGreenText = StatusGreenDark,
            statusAmberFill = StatusAmberMuted,
            statusAmberText = StatusAmberDark,
            statusRedFill = StatusRedMuted,
            statusRedText = StatusRedDark,
            pinkFill = PinkMuted,
            blueFill = BlueMuted
        )
    }

    //status bar color
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(LocalPetPalColors provides extraColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}