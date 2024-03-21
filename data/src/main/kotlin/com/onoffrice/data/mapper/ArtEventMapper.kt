package com.onoffrice.data.mapper

import com.onoffrice.data.database.ArtEventEntity
import com.onoffrice.data.model.ArtEventDetailResponse
import com.onoffrice.data.model.ArtEventMainResponse
import com.onoffrice.domain.model.ArtEvent

class ArtEventMapper {

    fun mapListToDomain(response: ArtEventMainResponse): List<ArtEvent> {
        return response.data.map {
            ArtEvent(
                title = it.title ?: "",
                id = it.id ?: "",
                description = it.description ?: "",
                shortDescription = it.shortDescription ?: "",
                origin = it.origin ?: "",
                location = it.location ?: "",
                dateDisplay = it.dateDisplay ?: "",
                imageUrl = it.imageUrl ?: "",
                startTime = it.startTime ?: "",
                endTime = it.endTime ?: "",
                startDate = it.startDate ?: "",
                endDate = it.endDate ?: "",
                isFavorite = false
            )
        }
    }

    fun mapToDomain(response: ArtEventDetailResponse): ArtEvent {
        return ArtEvent(
            title = response.artEventInfo?.title ?: "",
            id = response.artEventInfo?.id ?: "",
            description = response.artEventInfo?.description ?: "",
            shortDescription = response.artEventInfo?.shortDescription ?: "",
            origin = response.artEventInfo?.origin ?: "",
            location = response.artEventInfo?.location ?: "",
            dateDisplay = response.artEventInfo?.dateDisplay ?: "",
            imageUrl = response.artEventInfo?.imageUrl ?: "",
            startTime = response.artEventInfo?.startTime ?: "",
            endTime = response.artEventInfo?.endTime ?: "",
            startDate = response.artEventInfo?.startDate ?: "",
            endDate = response.artEventInfo?.endDate ?: "",
            isFavorite = false
        )
    }

    fun mapEntityToDomainArtEvent(entityList: List<ArtEventEntity>): List<ArtEvent> {
        return entityList.map {
            ArtEvent(
                id = it.id,
                title = it.title,
                isFavorite = it.isFavorite,
                imageUrl = it.imageUrl
            )
        }
    }

    fun mapFromDomainToEntity(domain: ArtEvent): ArtEventEntity {
        return ArtEventEntity(
            id = domain.id,
            title = domain.title,
            isFavorite = domain.isFavorite,
            imageUrl = domain.imageUrl
        )
    }
}
