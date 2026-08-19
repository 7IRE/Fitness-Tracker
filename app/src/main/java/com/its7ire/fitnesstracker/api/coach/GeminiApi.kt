package com.its7ire.fitnesstracker.api.coach

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface GeminiApi {

    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Header("x-goog-api-key") apiKey: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}