package com.onoffrice.core.resources

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ArtWorkAppDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 8.dp,
    val paddingLarge: Dp = 16.dp,
    val elevationDefault:Dp =  12.dp,
)

internal val LocalDimensions = staticCompositionLocalOf { ArtWorkAppDimensions() }
