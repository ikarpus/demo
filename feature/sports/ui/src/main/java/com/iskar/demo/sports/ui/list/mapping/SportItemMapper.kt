package com.iskar.demo.sports.ui.list.mapping

import com.iskar.demo.sports.domain.model.Sport
import com.iskar.demo.sports.ui.list.models.EventItem
import com.iskar.demo.sports.ui.list.models.SportItem

fun Sport.toSportItem(favoriteEvents: Set<String>): SportItem = SportItem(
    id = id,
    events = events
        .map { event -> event.toEventItem(favoriteEvents.contains(event.id)) }
        .sortedWith(compareByDescending<EventItem> { it.isFavorite }.thenBy { it.startTime })
)