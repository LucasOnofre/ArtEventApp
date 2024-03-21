package com.onoffrice.artevent.di

import com.onoffrice.artevent.ui.detail.ArtEventDetailViewModel
import com.onoffrice.artevent.ui.favorites.ArtEventFavoritesViewModel
import com.onoffrice.artevent.ui.list.ArtEventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val artEventModule = module {
    viewModel {
        ArtEventListViewModel(
            getArtwork = get(),
            dispatcher = get()
        )
    }
    viewModel {
        ArtEventFavoritesViewModel(
            getFavorites = get(),
            dispatcher = get(),
        )
    }
    viewModel {
        ArtEventDetailViewModel(
            dispatcher = get(),
            addFavorite = get(),
            removeFavorite = get(),
            getFavorites = get()
        )
    }
}