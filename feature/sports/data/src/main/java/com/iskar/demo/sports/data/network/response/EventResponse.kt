package com.iskar.demo.sports.data.network.response


import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("i")
    val id: String?,
    @SerializedName("d")
    val name: String?,
    @SerializedName("si")
    val sportId: String?,
    @SerializedName("tt")
    val startTime: Long?
)