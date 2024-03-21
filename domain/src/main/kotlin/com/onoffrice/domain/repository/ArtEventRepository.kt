package com.onoffrice.domain.repository

import androidx.paging.PagingData
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface ArtEventRepository {
    suspend fun getArtEvents(): Flow<PagingData<ArtEvent>>
    suspend fun getEventDetail(id: String): Flow<ResultWrapper<ArtEvent>>
    suspend fun getFavorites(): Flow<ResultWrapper<List<ArtEvent>>>
    suspend fun addFavorite(artEvent: ArtEvent): Flow<Unit>
    suspend fun removeFavorite(artEvent: ArtEvent): Flow<Unit>
}
