package com.iskar.demo.sports.domain.interactor

import com.iskar.demo.sports.domain.dataSource.SportDataSource

class SportInteractorImpl(
    private val sportDataSource: SportDataSource
) : SportInteractor {

    override val sportsFlow = sportDataSource.sportsFlow

    override suspend fun fetchSports() {
        sportDataSource.fetchSportsList()
    }

}