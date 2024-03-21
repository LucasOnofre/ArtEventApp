package com.onoffrice.domain.model

import java.io.Serializable


data class ArtEvent(
    val id: String = "",
    val location: String = "",
    val dateDisplay: String = "",
    val shortDescription: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val origin: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val title: String = "",
    var isFavorite: Boolean = false
): Serializable