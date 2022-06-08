package com.iskar.demo.sports.data.dataSource

import com.iskar.demo.core.utils.mapCatching
import com.iskar.demo.sports.data.maping.SportResponseMapper
import com.iskar.demo.sports.data.network.SportsApi
import com.iskar.demo.sports.domain.dataSource.SportDataSource
import com.iskar.demo.sports.domain.model.Sport
import kotlinx.coroutines.flow.MutableStateFlow

class SportDataSourceImpl(
    private val api: SportsApi,
    private val sportResponseMapper: SportResponseMapper,
) : SportDataSource {

    override val sportsFlow = MutableStateFlow<List<Sport>>(emptyList())

    override suspend fun fetchSportsList() {
        sportsFlow.value = api.getSports().mapCatching {
            sportResponseMapper.map(it)
        }
    }
}