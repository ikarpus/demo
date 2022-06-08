package com.iskar.demo.sports.domain.dataSource

import com.iskar.demo.sports.domain.model.Sport
import kotlinx.coroutines.flow.StateFlow

interface SportDataSource {

    val sportsFlow: StateFlow<List<Sport>>

    suspend fun fetchSportsList()

}