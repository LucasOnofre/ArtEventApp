package com.onoffrice.artevent.ui.detail

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.text.Html
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.onoffrice.artevent.ui.ARTWORK_DETAIL_ROUTE_ARG
import com.onoffrice.artevent.utils.getTime
import com.onoffrice.artevent.utils.htmlToString
import com.onoffrice.artevent.utils.toDate
import com.onoffrice.artwork.R
import com.onoffrice.core.resources.ArtEventAppTheme
import com.onoffrice.domain.model.ArtEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import java.util.Calendar

@ExperimentalAnimationApi
@Composable
fun ArtWorkDetailScreen(
    navController: NavHostController?,
) {
    val viewModel: ArtEventDetailViewModel = getViewModel()
    var context = LocalContext.current

    val favoriteState by viewModel.favoriteState.collectAsState()
    var showFavoriteAnimation by remember { mutableStateOf(false) }

    val selectedArtwork =
        navController?.previousBackStackEntry?.savedStateHandle?.get<ArtEvent?>(
            ARTWORK_DETAIL_ROUTE_ARG
        )
    viewModel.updateCurrentArtEvent(selectedArtwork)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = when (favoriteState) {
                    FavoriteState.FAVORITE -> Color.White
                    FavoriteState.UN_FAVORITE -> Color.Red
                    else -> Color.Red
                },
                onClick = {
                    showFavoriteAnimation = true
                    viewModel.favoriteAction()
                },
            ) {
                Icon(
                    imageVector =
                    when (favoriteState) {
                        FavoriteState.FAVORITE -> Icons.Filled.FavoriteBorder
                        FavoriteState.UN_FAVORITE -> Icons.Filled.Favorite
                        else -> Icons.Filled.Favorite
                    },
                    "Favorite button.",
                    tint = when (favoriteState) {
                        FavoriteState.FAVORITE -> Color.Red
                        FavoriteState.UN_FAVORITE -> Color.White
                        else -> Color.White
                    },
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.art_event_details_header_title))
                },
                contentColor = ArtEventAppTheme.colors.white,
                backgroundColor = ArtEventAppTheme.colors.blue,
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.addToCalendarAction()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Add to calendar",
                            tint = Color.White
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(ArtEventAppTheme.dimensions.paddingLarge)
            ) {
                selectedArtwork?.let { currentArtEventInfo ->
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = ArtEventAppTheme.dimensions.paddingMedium,
                                horizontal = ArtEventAppTheme.dimensions.paddingLarge
                            )
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(
                                    currentArtEventInfo.imageUrl
                                )
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(com.onoffrice.core.R.string.app_name),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(500.dp),
                            error = painterResource(id = R.drawable.error)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                        )
                        if (currentArtEventInfo.title.isNotEmpty()) {
                            Text(
                                text = currentArtEventInfo.title,
                                color = ArtEventAppTheme.colors.black333333,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            DefaultSpacer()
                        }
                        if (currentArtEventInfo.description.isNotEmpty()) {
                            currentArtEventInfo.description.htmlToString().let {
                                Text(
                                    text = it,
                                    color = ArtEventAppTheme.colors.black333333,
                                    fontSize = 16.sp,
                                )
                            }

                        }
                        Text(
                            modifier = Modifier.padding(bottom = 10.dp),
                            text = "Important info:",
                            color = ArtEventAppTheme.colors.black333333,
                            fontSize = 18.sp,
                            maxLines = 5,
                            fontWeight = FontWeight.Bold
                        )
                        if (currentArtEventInfo.dateDisplay.isNotEmpty()) {
                            Row(
                                Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Date Display",
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = currentArtEventInfo.dateDisplay,
                                    color = ArtEventAppTheme.colors.black333333,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
            LaunchedEffect(key1 = favoriteState) {
                delay(2000)
                showFavoriteAnimation = false
            }

            AnimatedVisibility(visible = showFavoriteAnimation) {
                FavoriteAnimation(favoriteState)
            }
        }

        LaunchedEffect(key1 = Unit) {
            viewModel.addToCalendarEvent.collectLatest {
                addToCalendar(context, it)
            }
        }
    }
}

fun addToCalendar(context: Context, artEvent: ArtEvent) {
    val startMillis: Long = Calendar.getInstance().run {
        val startDate = artEvent.startDate.toDate()
        time = startDate
        set(Calendar.HOUR_OF_DAY, artEvent.startTime.getTime().hour)
        set(Calendar.MINUTE, artEvent.endTime.getTime().minute)
        timeInMillis
    }
    val endMillis: Long = Calendar.getInstance().run {
        val endDate = artEvent.endDate.toDate()
        time = endDate
        set(Calendar.HOUR_OF_DAY, artEvent.endTime.getTime().hour)
        set(Calendar.MINUTE, artEvent.endTime.getTime().minute)
        timeInMillis
    }
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
        .putExtra(CalendarContract.Events.TITLE, artEvent.title)
        .putExtra(CalendarContract.Events.EVENT_LOCATION, artEvent.location)
        .putExtra(CalendarContract.Events.DESCRIPTION, artEvent.shortDescription.htmlToString())
        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
    startActivity(context, intent, null)
}

@Composable
private fun FavoriteAnimation(favoriteState: FavoriteState) {
    when (favoriteState) {
        FavoriteState.FAVORITE -> Box {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = ""
            )
        }

        FavoriteState.UN_FAVORITE -> Box {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp),
                painter = painterResource(id = R.drawable.unfavorite),
                contentDescription = ""
            )
        }

        FavoriteState.NONE -> Unit
    }
}

@Composable
private fun DefaultSpacer(spacing: Dp = 10.dp) {
    Spacer(
        modifier = Modifier
            .height(spacing)
            .fillMaxWidth()
    )
}
