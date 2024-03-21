package com.onoffrice.core.resources

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object ArtEventAppTheme {
    val colors: ArtWorkAppColors
        @Composable @ReadOnlyComposable get() = LocalColors.current
    val dimensions: ArtWorkAppDimensions
        @Composable @ReadOnlyComposable get() = LocalDimensions.current
}

@Composable
fun ArtWorkAppTheme(
    colors: ArtWorkAppColors = ArtEventAppTheme.colors,
    dimensions: ArtWorkAppDimensions = ArtEventAppTheme.dimensions,
    content: @Composable () -> Unit
) {
    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb() // change color status bar here
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimensions provides dimensions
    ) {
        content()
    }
}
