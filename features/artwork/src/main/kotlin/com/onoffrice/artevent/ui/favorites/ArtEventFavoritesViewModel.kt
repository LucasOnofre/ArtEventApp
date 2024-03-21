package com.onoffrice.artevent.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.core.extensions.wrapResponse
import com.onoffrice.core.utils.UIState
import com.onoffrice.domain.interactors.GetFavorites
import com.onoffrice.domain.model.ApiError
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ArtEventFavoritesViewModel(
    private val getFavorites: GetFavorites,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _favorites = MutableStateFlow<UIState<List<ArtEvent>>>(UIState.Loading)
    val favorites = _favorites.asStateFlow()


    fun getFavorites() {
        viewModelScope.launch(dispatcher.io) {
            getFavorites.execute()
                .catch {
                    _favorites.value =
                        UIState.Error(ApiError(it.message ?: "Generic Error"))
                }
                .flowOn(dispatcher.io)
                .collect {
                    _favorites.wrapResponse(it)
                }
        }
    }
}