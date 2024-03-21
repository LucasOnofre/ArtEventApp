package com.onoffrice.core.resources

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class ArtWorkAppColors(
    background: Color,
    black333333: Color,
    blue: Color,
    white:Color,
    red:Color
) {
    var background by mutableStateOf(background)
        private set
    var black333333 by mutableStateOf(black333333)
        private set
    var blue by mutableStateOf(blue)
        private set

    var white by mutableStateOf(white)
        private set

    var red by mutableStateOf(white)
        private set

    fun copy(
        background: Color = this.background,
        black333333: Color = this.black333333,
        blue: Color = this.blue,
        white: Color = this.white,
    ): ArtWorkAppColors = ArtWorkAppColors(
        background = background,
        black333333 = black333333,
        blue = blue,
        white = white,
        red = red
    )

    fun updateColorsFrom(other: ArtWorkAppColors) {
        background = other.background
        black333333 = other.black333333
        blue = other.blue
        white = other.white
        red = other.red
    }
}

private val colorLightBackground = Color(0xFFF7F5F6)
private val colorLightBlack333333 = Color(0xFF000000)
val colorLightBlue006A4D = Color(0xFF3A4CB4)
val colorWhite = Color(0xFFFFFFFF)
val red = Color(0xFFCF0F0F)

fun colors(
    background: Color = colorLightBackground,
    black333333: Color = colorLightBlack333333,
    blue: Color = colorLightBlue006A4D,
    white: Color = colorWhite,
): ArtWorkAppColors = ArtWorkAppColors(
    background = background,
    black333333 = black333333,
    blue = blue,
    white = white,
    red = red
)

val LocalColors = staticCompositionLocalOf { colors() }
