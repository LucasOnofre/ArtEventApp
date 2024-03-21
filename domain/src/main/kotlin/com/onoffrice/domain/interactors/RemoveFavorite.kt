package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.repository.ArtEventRepository
import kotlinx.coroutines.flow.Flow

class RemoveFavoriteImpl(
    private val repository: ArtEventRepository
) : RemoveFavorite {
    override suspend fun execute(artEvent: ArtEvent): Flow<Unit> {
        return repository.removeFavorite(artEvent.apply {
            isFavorite = false
        })
    }
}

interface RemoveFavorite {
    suspend fun execute(artEvent: ArtEvent): Flow<Unit>
}