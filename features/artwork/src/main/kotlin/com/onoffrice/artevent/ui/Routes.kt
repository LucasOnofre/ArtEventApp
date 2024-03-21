package com.onoffrice.artevent.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

const val ARTWORK_DETAIL_ROUTE_ARG = "ARTWORK"

sealed class Routes(val rout: String, val icon: ImageVector, val text: String) {
    object ArtworkList : Routes("artwork_list", Icons.Default.List, "List")
    object ArtworkDetail : Routes("artwork_detail", Icons.Default.Info, "Detail")
    object Favorites : Routes("artwork_favorites", Icons.Default.Favorite, "Favorites")
}