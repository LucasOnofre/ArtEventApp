package com.onoffrice.data.di

import com.onoffrice.data.repository.ArtEventRepositoryImpl
import com.onoffrice.domain.repository.ArtEventRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ArtEventRepository> { ArtEventRepositoryImpl(api = get(), database = get()) }
}
