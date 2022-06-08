package com.iskar.demo.di

import com.iskar.demo.core.data.di.networkModule
import com.iskar.demo.sports.data.di.sportsDataModule
import com.iskar.demo.sports.domain.di.sportsDomainModule
import com.iskar.demo.sports.ui.di.sportsUiModule

object KoinModules {
    val modules = listOf(
        networkModule,
        sportsUiModule,
        sportsDataModule,
        sportsDomainModule,
    )
}