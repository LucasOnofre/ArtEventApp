package com.onoffrice.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.onoffrice.data.api.ArtEventApi
import com.onoffrice.data.mapper.ArtEventMapper
import com.onoffrice.domain.model.ArtEvent
import retrofit2.HttpException
import java.io.IOException

class ArtEventPagingSource(
    val api: ArtEventApi
) : PagingSource<Int, ArtEvent>() {
    override fun getRefreshKey(state: PagingState<Int, ArtEvent>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtEvent> {
        return try {
            val currentPage = params.key ?: 1
            val artEventResponse = api.getArtworks(
                page = currentPage
            )
            LoadResult.Page(
                data = ArtEventMapper().mapListToDomain(artEventResponse),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey =
                if (artEventResponse.data.isEmpty()) null
                else artEventResponse.pagination?.currentPage!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}