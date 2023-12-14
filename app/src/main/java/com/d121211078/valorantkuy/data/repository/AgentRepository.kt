package com.d121211078.valorantkuy.data.repository

import com.d121211078.valorantkuy.data.response.GetAgentResponse
import com.d121211078.valorantkuy.data.source.remote.ApiService

class AgentRepository (private val apiService: ApiService) {

    suspend fun getAgents(): GetAgentResponse {
        return apiService.getAgents()
    }
}