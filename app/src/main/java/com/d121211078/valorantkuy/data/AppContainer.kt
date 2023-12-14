package com.d121211078.valorantkuy.data

import com.d121211078.valorantkuy.data.repository.AgentRepository
import com.d121211078.valorantkuy.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import kotlinx.serialization.json.Json
//import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val agentRepository: AgentRepository
}

class  DefaultAppContainer: AppContainer {

    private val BASE_URL = "https://valorant-api.com"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val agentRepository: AgentRepository
        get() = AgentRepository(retrofitService)
}