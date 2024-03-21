package com.onoffrice.data.model

import com.google.gson.annotations.SerializedName

class ArtEventDetailResponse(
    @SerializedName("data") val artEventInfo: ArtEventResponse? = null,
)