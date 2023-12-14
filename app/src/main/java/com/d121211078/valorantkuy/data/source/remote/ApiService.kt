package com.d121211078.valorantkuy.data.source.remote

import com.d121211078.valorantkuy.data.response.GetAgentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/agents")
    suspend fun getAgents(
        @Query("status") status: Int = 200,
    ): GetAgentResponse
}