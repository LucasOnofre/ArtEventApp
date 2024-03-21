package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.repository.ArtEventRepository
import kotlinx.coroutines.flow.Flow

class AddFavoriteImpl(
    private val repository: ArtEventRepository
) : AddFavorite {
    override suspend fun execute(artEvent: ArtEvent): Flow<Unit> {
        return repository.addFavorite(artEvent.apply {
            isFavorite = true
        })
    }
}

interface AddFavorite {
    suspend fun execute(artEvent: ArtEvent): Flow<Unit>
}