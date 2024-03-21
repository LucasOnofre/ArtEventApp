package com.onoffrice.domain.interactors

import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.repository.ArtEventRepository
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

class GetFavoritesImpl(
    private val repository: ArtEventRepository
) : GetFavorites {
    override suspend fun execute(): Flow<ResultWrapper<List<ArtEvent>>> {
        return repository.getFavorites()
    }
}

interface GetFavorites {
    suspend fun execute(): Flow<ResultWrapper<List<ArtEvent>>>
}