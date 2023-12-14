package com.d121211078.valorantkuy.data.response

import com.d121211078.valorantkuy.data.models.Agent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAgentResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("data")
    val data: List<Agent>,
)