package com.onoffrice.artevent.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onoffrice.domain.interactors.AddFavorite
import com.onoffrice.domain.interactors.GetFavorites
import com.onoffrice.domain.interactors.RemoveFavorite
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.utils.DispatcherProvider
import com.onoffrice.domain.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

class ArtEventDetailViewModel(
    private val getFavorites: GetFavorites,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private var currentArtEvent: ArtEvent? = null

    private val _favoriteState: MutableStateFlow<FavoriteState> =
        MutableStateFlow(value = FavoriteState.NONE)
    val favoriteState: MutableStateFlow<FavoriteState> get() = _favoriteState

    private val _addToCalendarEvent = MutableSharedFlow<ArtEvent>()
    val addToCalendarEvent = _addToCalendarEvent.asSharedFlow()

    fun updateCurrentArtEvent(artEvent: ArtEvent?) {
        artEvent?.let {
            currentArtEvent = artEvent
            getIsFavorite()
        }
    }

    private fun handleFavoriteState() {
        currentArtEvent?.isFavorite?.let {
            _favoriteState.value =
                if (it) FavoriteState.FAVORITE else FavoriteState.UN_FAVORITE
        }
    }

    private fun addFavorite() {
        _favoriteState.value = FavoriteState.FAVORITE

        currentArtEvent?.let {
            viewModelScope.launch(dispatcher.io) {
                addFavorite.execute(it)
                    .launchIn(this)
            }
        }
    }

    private fun removeFavorite() {
        _favoriteState.value = FavoriteState.UN_FAVORITE

        currentArtEvent?.let {
            viewModelScope.launch(dispatcher.io) {
                removeFavorite.execute(it)
                    .launchIn(this)
            }
        }
    }

    fun favoriteAction() {
        currentArtEvent?.let {
            if (it.isFavorite) {
                removeFavorite()
            } else {
                addFavorite()
            }
        }
    }

    private fun getIsFavorite() {
        viewModelScope.launch(dispatcher.io) {
            getFavorites.execute()
                .flowOn(dispatcher.io)
                .collect {
                    checkIfCurrentArtworkIsFavorite(it)
                }
        }
    }

    private fun checkIfCurrentArtworkIsFavorite(
        it: ResultWrapper<List<ArtEvent>>
    ) {
        val response = it as ResultWrapper.Success
        response.value.forEach {
            if (it.isFavorite) {
                currentArtEvent?.isFavorite = true
            }
        }
        handleFavoriteState()
    }

    fun addToCalendarAction() {
        viewModelScope.launch(Dispatchers.Main) {
            currentArtEvent?.let { _addToCalendarEvent.emit(it) }
        }
    }
}

enum class FavoriteState {
    FAVORITE, UN_FAVORITE, NONE
}