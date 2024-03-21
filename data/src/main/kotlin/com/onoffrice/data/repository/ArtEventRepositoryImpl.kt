package com.onoffrice.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onoffrice.data.api.ArtEventApi
import com.onoffrice.data.database.ArtEventDatabase
import com.onoffrice.data.mapper.ArtEventMapper
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.repository.ArtEventRepository
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArtEventRepositoryImpl(
    val api: ArtEventApi,
    val database: ArtEventDatabase
) : ArtEventRepository {
    private val dao = database.artworkDao()

    override suspend fun getArtEvents(): Flow<PagingData<ArtEvent>> {
        return Pager(
            config = PagingConfig(10, prefetchDistance = 2),
            pagingSourceFactory = {
                ArtEventPagingSource(api)
            }
        ).flow
    }

    override suspend fun getEventDetail(id: String): Flow<ResultWrapper<ArtEvent>> {
        return flow {
            try {
                emit(
                    ResultWrapper.Success(
                        ArtEventMapper().mapToDomain(api.getArtworkDetail(id))
                    )
                )
            } catch (ex: Exception) {
                emit(ResultWrapper.Error())
            }
        }
    }

    override suspend fun getFavorites(): Flow<ResultWrapper<List<ArtEvent>>> {
        return flow {
            try {
                emit(
                    ResultWrapper.Success(
                        ArtEventMapper().mapEntityToDomainArtEvent(
                            dao.getFavorites()
                        )
                    )
                )
            } catch (ex: Exception) {
                emit(ResultWrapper.Error())
            }
        }
    }

    override suspend fun addFavorite(artEvent: ArtEvent): Flow<Unit> {
        return flow {
            emit(
                dao.addFavorite(ArtEventMapper().mapFromDomainToEntity(artEvent))
            )
        }
    }

    override suspend fun removeFavorite(artEvent: ArtEvent): Flow<Unit> {
        return flow {
            emit(
                dao.removeFavorite(ArtEventMapper().mapFromDomainToEntity(artEvent))
            )
        }
    }
}
