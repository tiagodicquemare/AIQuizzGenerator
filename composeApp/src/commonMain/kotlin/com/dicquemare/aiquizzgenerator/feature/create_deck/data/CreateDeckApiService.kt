package com.dicquemare.aiquizzgenerator.feature.create_deck.data

import com.dicquemare.aiquizzgenerator.core.data.network.NetworkConfig
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto.CreateDeckDto
import com.dicquemare.aiquizzgenerator.feature.create_deck.data.dto.DeckDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CreateDeckApiService(private val client: HttpClient) {
    private val baseUrl = NetworkConfig.LOCAL_IP
    suspend fun createDeck(request: CreateDeckDto): DeckDto {
        return client.post("$baseUrl/create_quiz") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}