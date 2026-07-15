package org.example

import java.net.http.HttpRequest
import java.net.http.HttpResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.net.URI
import java.net.http.HttpClient


import java.time.Duration

class DeepLClient(
    private val apiKey: String,
    private val apiUrl: String
) {
    private val httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build()

    private val objectMapper = jacksonObjectMapper()

    fun translate(
        text: String,
        targetLanguage: String
    ): DeepLDTO.DeepLTranslateResponse {
        val requestBody = DeepLDTO.DeepLTranslateRequest(
            text = listOf(text),
            targetLang = targetLanguage
        )

        val request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .timeout(Duration.ofSeconds(30))
            .header(
                "Authorization",
                "DeepL-Auth-Key $apiKey"
            )
            .header("Content-Type", "application/json")
            .POST(
                HttpRequest.BodyPublishers.ofString(
                    objectMapper.writeValueAsString(requestBody)
                )
            )
            .build()

        val response = httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        )

        if (response.statusCode() !in 200..299) {
            error(
                "DeepL API 호출 실패: " +
                        "${response.statusCode()} ${response.body()}"
            )
        }

        return objectMapper.readValue(response.body())
    }
}

