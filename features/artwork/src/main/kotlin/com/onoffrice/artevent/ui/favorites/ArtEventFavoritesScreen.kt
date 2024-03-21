package com.onoffrice.artevent.ui.favorites

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import com.onoffrice.artevent.ui.ARTWORK_DETAIL_ROUTE_ARG
import com.onoffrice.artevent.ui.Routes
import com.onoffrice.artevent.utils.ArtEventItem
import com.onoffrice.artevent.utils.createDialog
import com.onoffrice.artwork.R
import com.onoffrice.core.resources.ArtEventAppTheme
import com.onoffrice.core.utils.UIState
import com.onoffrice.core.widget.LoadingWidget
import org.koin.androidx.compose.getViewModel

@Composable
fun ArtEventFavoritesScreen(navController: NavHostController?) {

    val context = LocalContext.current
    val viewModel: ArtEventFavoritesViewModel = getViewModel()
    val state by viewModel.favorites.collectAsState(initial = UIState.Loading)


    LaunchedEffect(key1 = null) {
        viewModel.getFavorites()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.art_favorites_header_title))
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
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Crossfade(
                modifier = Modifier.padding(),
                targetState = state,
                label = ""
            ) { favoritesState ->
                when (favoritesState) {
                    is UIState.Loading ->
                        LoadingWidget(
                            loading = true, modifier = Modifier.fillMaxSize()
                        )

                    is UIState.Success -> {
                        val items = favoritesState.data

                        Column(
                            modifier = Modifier
                                .padding(
                                    vertical = ArtEventAppTheme.dimensions.paddingMedium,
                                    horizontal = ArtEventAppTheme.dimensions.paddingLarge
                                )
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(
                                    bottom = ArtEventAppTheme.dimensions.paddingLarge
                                )
                            ) {
                                if (items.isEmpty()) {
                                    item {
                                        Box(
                                            Modifier
                                                .fillMaxSize()
                                        ) {
                                            Text(
                                                text = "No Favorites",
                                                color = ArtEventAppTheme.colors.black333333,
                                                textAlign = TextAlign.Center,
                                                fontSize = 26.sp,
                                                modifier = Modifier
                                                    .align(Alignment.Center)
                                                    .padding(all = ArtEventAppTheme.dimensions.paddingLarge)
                                            )
                                        }
                                    }
                                } else {
                                    items(items.size) { index ->
                                        val artwork = items[index]
                                        ArtEventItem(
                                            artEvent = artwork,
                                            onSelectArtEvent = {
                                                navController?.currentBackStackEntry?.savedStateHandle?.set(
                                                    ARTWORK_DETAIL_ROUTE_ARG, artwork
                                                )

                                                navController?.navigate(
                                                    Routes.ArtworkDetail.rout
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        LaunchedEffect(true) {
                            val error = (favoritesState as? Error)?.message
                            if (error != null) {
                                context.createDialog(
                                    title = R.string.unknown_error,
                                    message = error,
                                    positiveButton = R.string.ok
                                ) { dialog ->
                                    dialog.dismiss()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DisplayError(context: Context, error: LoadState.Error) {
    context.createDialog(
        title = R.string.unknown_error,
        message = error.error.message ?: "",
        positiveButton = R.string.ok
    ) { dialog ->
        dialog.dismiss()
    }
}
