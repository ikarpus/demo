package com.iskar.demo.sports.data.maping

import com.iskar.demo.core.utils.SuspendableMapper
import com.iskar.demo.core.utils.mapCatching
import com.iskar.demo.sports.data.network.response.EventResponse
import com.iskar.demo.sports.data.network.response.SportResponse
import com.iskar.demo.sports.domain.model.Event
import com.iskar.demo.sports.domain.model.Sport

class SportResponseMapper(
    private val eventResponseMapper: EventResponseMapper
) : SuspendableMapper<SportResponse, Sport> {
    override suspend fun map(input: SportResponse): Sport = Sport(
        id = checkNotNull(input.id),
        name = checkNotNull(input.name),
        events = input.events?.mapCatching { eventResponseMapper.map(it) } ?: emptyList()
    )
}

class EventResponseMapper : SuspendableMapper<EventResponse, Event> {
    override suspend fun map(input: EventResponse): Event = Event(
        id = checkNotNull(input.id),
        name = input.name,
        sportId = checkNotNull(input.sportId),
        startTime = input.startTime
    )
}