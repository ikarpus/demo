package com.iskar.demo.sports.domain.interactor

import com.iskar.demo.sports.domain.model.Sport
import kotlinx.coroutines.flow.StateFlow

interface SportInteractor {

    val sportsFlow: StateFlow<List<Sport>>

    suspend fun fetchSports()

}