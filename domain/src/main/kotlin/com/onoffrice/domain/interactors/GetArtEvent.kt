package com.onoffrice.domain.interactors

import androidx.paging.PagingData
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.repository.ArtEventRepository
import kotlinx.coroutines.flow.Flow

class GetArtEventImpl(
    private val repository: ArtEventRepository) : GetArtEvent {
    override suspend fun execute(): Flow<PagingData<ArtEvent>> {
        return repository.getArtEvents()
    }
}

interface GetArtEvent {
    suspend fun execute(): Flow<PagingData<ArtEvent>>
}