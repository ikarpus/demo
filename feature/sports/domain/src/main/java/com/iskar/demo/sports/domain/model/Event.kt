package com.iskar.demo.sports.domain.model

data class Event(
    val id: String,
    val name: String?,
    val sportId: String,
    val startTime: Long?,
    val isFavorite: Boolean = false
)