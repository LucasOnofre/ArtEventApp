package com.onoffrice.data.api

import com.onoffrice.data.BuildConfig
import com.onoffrice.data.model.ArtEventDetailResponse
import com.onoffrice.data.model.ArtEventMainResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtEventApi {
    @GET("events")
    @BaseUrl(BuildConfig.BASE_URL)
    suspend fun getArtworks(
        @Query("page") page: Int,
    ): ArtEventMainResponse

    @GET("events/{id}")
    @BaseUrl(BuildConfig.BASE_URL)
    suspend fun getArtworkDetail(
        @Path("id") artId: String
    ): ArtEventDetailResponse
}

