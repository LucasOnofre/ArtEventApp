package com.onoffrice.data.model

import com.google.gson.annotations.SerializedName

class ArtEventMainResponse(
    @SerializedName("data") val data: List<ArtEventResponse> = listOf(),
    @SerializedName("pagination") val pagination: PaginationResponse? = null
)

class PaginationResponse {
    @SerializedName("current_page") val currentPage: Int? = null
}

class ArtEventResponse(
    @SerializedName("id") val id: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("date_display") val dateDisplay: String? = null,
    @SerializedName("short_description") val shortDescription: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("place_of_origin") val origin: String? = null,
    @SerializedName("start_time") val startTime: String? = null,
    @SerializedName("end_time") val endTime: String? = null,
    @SerializedName("start_date") val startDate: String? = null,
    @SerializedName("end_date") val endDate: String? = null,
    @SerializedName("title") val title: String? = null
)

