package com.its7ire.fitnesstracker.data.coach

import android.util.Log
import com.its7ire.fitnesstracker.BuildConfig
import com.its7ire.fitnesstracker.api.coach.Content
import com.its7ire.fitnesstracker.api.coach.GeminiRequest
import com.its7ire.fitnesstracker.api.coach.GeminiRetrofit
import com.its7ire.fitnesstracker.api.coach.Part

class GeminiRepo {

    private val api = GeminiRetrofit.api

    private val conversation = mutableListOf<Content>()

    suspend fun askCoach(question: String): String {

        val formattedQuestion = """
            You are a professional fitness coach inside a fitness tracking app.

            Answer the user's question clearly, concisely, and professionally.

            FOLLOW THESE FORMATTING RULES:

            - Start with a short title.
            - Keep the answer concise.
            - Use short paragraphs.
            - Use simple section headings when useful.
            - Use bullet points for lists.
            - Use numbered lists for steps.
            - Do NOT USE # or unnecessary symbols
            - For workouts, format exercises as:
               Exercise — Sets × Reps
            - For nutrition questions, separate meals or food items clearly.
            - Add a "Tip" section when useful.
            - Add a "Note" section only when necessary.
            - Do NOT use markdown tables.
            - Do NOT use excessive emojis.
            - Do NOT repeat the user's question.
            - Do NOT write huge paragraphs.
            - Do NOT include unnecessary explanations.
            - Give practical and safe fitness advice.

            USER QUESTION:
            $question
        """.trimIndent()

        conversation.add(
            Content(
                role = "user",
                parts = listOf(
                    Part(text = formattedQuestion)
                )
            )
        )

        val request = GeminiRequest(
            contents = conversation
        )

        return try {

            val response = api.generateContent(
                apiKey = BuildConfig.GEMINI_API_KEY,
                request = request
            )

            val answer = response
                .candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text
                ?: "I couldn't generate a response."

            conversation.add(
                Content(
                    role = "model",
                    parts = listOf(
                        Part(text = answer)
                    )
                )
            )

            answer

        } catch (e: Exception) {

            Log.e(
                "GEMINI_ERROR",
                "Gemini request failed",
                e
            )

            conversation.removeLastOrNull()

            "Something went wrong. Please try again."
        }
    }
}