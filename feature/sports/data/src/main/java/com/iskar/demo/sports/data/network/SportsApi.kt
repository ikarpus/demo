package com.iskar.demo.sports.data.network

import com.iskar.demo.sports.data.network.response.SportResponse
import retrofit2.http.GET

interface SportsApi {

    @GET("/api/v1/demo")
    suspend fun getSports(): List<SportResponse>
}