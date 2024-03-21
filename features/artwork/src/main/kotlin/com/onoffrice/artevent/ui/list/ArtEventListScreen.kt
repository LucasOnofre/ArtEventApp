package com.onoffrice.artevent.ui.list

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.onoffrice.artevent.ui.ARTWORK_DETAIL_ROUTE_ARG
import com.onoffrice.artevent.ui.Routes
import com.onoffrice.artevent.utils.ArtEventItem
import com.onoffrice.artevent.utils.createDialog
import com.onoffrice.artwork.R
import com.onoffrice.core.resources.ArtWorkAppTheme
import com.onoffrice.domain.model.ArtEvent
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArtEventListScreen(navController: NavHostController?) {

    val context = LocalContext.current
    val viewModel: ArtEventListViewModel = getViewModel()
    val refreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(refreshing, { viewModel.refresh() })

    LaunchedEffect(key1 = null) {
        viewModel.getArtwork()
    }

    ArtWorkAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val artwork: LazyPagingItems<ArtEvent> = viewModel.artEvent.collectAsLazyPagingItems()
            LazyColumn(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
                    .padding(10.dp)
            ) {
                items(artwork.itemCount) { index ->
                    val item = artwork[index]
                    ArtEventItem(
                        artEvent = item,
                        onSelectArtEvent = {
                            item?.let {
                                navController?.currentBackStackEntry?.savedStateHandle?.set(ARTWORK_DETAIL_ROUTE_ARG, it)
                                navController?.navigate(
                                    Routes.ArtworkDetail.rout
                                )
                            }
                        }
                    )
                }
                artwork.apply {
                    when {
                        loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }

                        loadState.refresh is LoadState.Error -> {
                            val error = artwork.loadState.refresh as LoadState.Error

                            item {
                                context.createDialog(
                                    title = R.string.unknown_error,
                                    message = error.error.message ?: "",
                                    positiveButton = R.string.ok
                                ) { dialog ->
                                    dialog.dismiss()
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val error = artwork.loadState.append as LoadState.Error
                            item {
                                DisplayError(context, error)
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing,
                pullRefreshState,
                Modifier.align(Alignment.Center)
            )
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
