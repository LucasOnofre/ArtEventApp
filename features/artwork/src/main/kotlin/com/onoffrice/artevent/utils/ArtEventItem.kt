package com.onoffrice.artevent.utils

import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onoffrice.artevent.Constants
import com.onoffrice.artwork.R
import com.onoffrice.core.resources.ArtEventAppTheme
import com.onoffrice.domain.model.ArtEvent

@Composable
fun ArtEventItem(artEvent: ArtEvent?, onSelectArtEvent: () -> Unit) {
    artEvent?.let {
        Box(
            modifier = Modifier
                .padding(ArtEventAppTheme.dimensions.paddingMedium)
                .shadow(ArtEventAppTheme.dimensions.elevationDefault, RectangleShape)
        ) {
            Column(
                modifier = Modifier
                    .clickable { onSelectArtEvent.invoke() }
                    .fillMaxSize()
                    .background(ArtEventAppTheme.colors.background)
                    .padding(ArtEventAppTheme.dimensions.paddingLarge)

            ) {
                Row {
                    ArtEventItem(
                        content = artEvent
                    )
                }
                Spacer(modifier = Modifier.size(ArtEventAppTheme.dimensions.paddingSmall))
            }
        }
    }
}

@Composable
private fun ArtEventItem(content: ArtEvent?) {
    Column {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    content?.imageUrl
                )
                .crossfade(true)
                .build(),
            contentDescription = stringResource(com.onoffrice.core.R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp),
            error = painterResource(id = R.drawable.error)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .fillMaxWidth()
        )
        Text(
            text = content?.title ?: "",
            color = ArtEventAppTheme.colors.black333333,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth()
        )
        val text = Html.fromHtml(content?.shortDescription ?: "", Html.FROM_HTML_MODE_COMPACT).toString()
        Text(
            text = text,
            color = ArtEventAppTheme.colors.black333333,
            fontSize = 14.sp,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}