package com.onoffrice.artevent.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onoffrice.domain.interactors.GetArtEvent
import com.onoffrice.domain.model.ArtEvent
import com.onoffrice.domain.utils.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ArtEventListViewModel(
    private val getArtwork: GetArtEvent,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    private val _artEvent: MutableStateFlow<PagingData<ArtEvent>> = MutableStateFlow(value = PagingData.empty())
    val artEvent: MutableStateFlow<PagingData<ArtEvent>> get() = _artEvent


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getArtwork() {
        viewModelScope.launch(dispatcher.io) {
            getArtwork.execute()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    _artEvent.value = it
                }
                _isRefreshing.emit(false)
        }
    }

    fun refresh() {
        getArtwork()
    }
}