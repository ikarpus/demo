package com.iskar.demo.sports.ui

import androidx.lifecycle.asLiveData
import com.iskar.demo.core.ui.base.BaseViewModel
import com.iskar.demo.sports.domain.interactor.SportInteractor
import com.iskar.demo.sports.ui.list.mapping.toHeaderItem
import com.iskar.demo.sports.ui.list.mapping.toSportItem
import com.iskar.demo.sports.ui.list.models.SportsListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn

class SportsViewModel(
    private val sportInteractor: SportInteractor,
) : BaseViewModel() {

    private val expandedIds = MutableStateFlow<Set<String>>(setOf())
    private val favoriteEvents = MutableStateFlow<Set<String>>(setOf())

    val sports = combine(
        sportInteractor.sportsFlow,
        expandedIds,
        favoriteEvents
    ) { sports, expandedSports, favoriteEvents ->
        with(mutableListOf<SportsListItem>()) {
            sports.forEach { sport ->
                val isExpanded = expandedSports.none { it == sport.id }
                add(sport.toHeaderItem(isExpanded))
                if (isExpanded) {
                    add(sport.toSportItem(favoriteEvents))
                }
            }
            this
        }
    }
        .flowOn(defaultContext)
        .asLiveData()

    init {
        fetchSports()
    }

    fun fetchSports() {
        launch(dispatcher = ioContext) {
            sportInteractor.fetchSports()
        }
    }

    fun swapExpandedStatus(id: String) {
        updateState(expandedIds, id)
    }

    fun swapFavoriteStatus(id: String) {
        updateState(favoriteEvents, id)
    }

    private fun updateState(state: MutableStateFlow<Set<String>>, id: String) {
        val set = state.value.toMutableSet()
        if (state.value.contains(id)) {
            set.remove(id)
        } else {
            set.add(id)
        }
        state.value = set
    }

}