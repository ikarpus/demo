package com.iskar.demo.sports.ui.list.mapping

import com.iskar.demo.sports.domain.model.Event
import com.iskar.demo.sports.ui.list.models.EventItem

fun Event.toEventItem(isFavorite: Boolean = false): EventItem = EventItem(
    id = id,
    playerOne = name?.substringBefore("-")?.trim(),
    playerTwo = name?.substringAfter("-")?.trim(),
    startTime = startTime,
    isFavorite = isFavorite
)