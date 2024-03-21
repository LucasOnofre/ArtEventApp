package com.onoffrice.core.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.onoffrice.core.resources.ArtEventAppTheme

@Composable
fun LoadingWidget(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    progressAlignment: Alignment = Alignment.Center,
    backgroundColor: Color = ArtEventAppTheme.colors.background
) {
    AnimatedVisibility(visible = loading, enter = fadeIn(), exit = fadeOut()) {
        Box(modifier = modifier.background(color = backgroundColor)) {
            CircularProgressIndicator(
                color = ArtEventAppTheme.colors.blue,
                modifier = Modifier.align(progressAlignment)
            )
        }
    }
}
