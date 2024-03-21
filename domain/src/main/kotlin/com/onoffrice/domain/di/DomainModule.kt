package com.onoffrice.domain.di

import com.onoffrice.domain.interactors.AddFavorite
import com.onoffrice.domain.interactors.AddFavoriteImpl
import com.onoffrice.domain.interactors.GetArtEvent
import com.onoffrice.domain.interactors.GetArtEventImpl
import com.onoffrice.domain.interactors.GetFavorites
import com.onoffrice.domain.interactors.GetFavoritesImpl
import com.onoffrice.domain.interactors.RemoveFavorite
import com.onoffrice.domain.interactors.RemoveFavoriteImpl
import com.onoffrice.domain.utils.DefaultDispatchers
import com.onoffrice.domain.utils.DispatcherProvider
import org.koin.dsl.module

val domainModule = module {
    factory<GetArtEvent> { GetArtEventImpl(repository = get()) }
    factory<GetFavorites> { GetFavoritesImpl(repository = get()) }
    factory<AddFavorite> { AddFavoriteImpl(repository = get()) }
    factory<RemoveFavorite> { RemoveFavoriteImpl(repository = get()) }
    factory<DispatcherProvider> { DefaultDispatchers() }
}
