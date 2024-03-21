package com.onoffrice

import com.onoffrice.core.di.coreModule
import com.onoffrice.data.di.dataModule
import com.onoffrice.data.di.repositoryModule
import com.onoffrice.domain.di.domainModule
import com.onoffrice.artevent.di.artEventModule

val modules = listOf(
    dataModule,
    repositoryModule,
    domainModule,
    coreModule,
    artEventModule
)
