package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.DateUtils
import com.its7ire.fitnesstracker.api.coach.GeminiRepo
import com.its7ire.fitnesstracker.composable.coach.CoachChatMessageData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoachViewModel : ViewModel() {

    private val repository = GeminiRepo()

    private val _messages = MutableStateFlow<List<CoachChatMessageData>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun askCoach(question: String) {
        val trimmed = question.trim()
        if (trimmed.isBlank()) return

        val userMessage = CoachChatMessageData(
            message = trimmed,
            isUser = true,
            time = DateUtils.getCurrentTime().take(5)
        )

        _messages.value = _messages.value + userMessage
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val answer = repository.askCoach(trimmed)
                val coachMessage = CoachChatMessageData(
                    message = answer,
                    isUser = false,
                    time = DateUtils.getCurrentTime().take(5)
                )
                _messages.value = _messages.value + coachMessage
            } catch (e: Exception) {
                val errorMessage = CoachChatMessageData(
                    message = "Sorry, something went wrong. Please try again.",
                    isUser = false,
                    time = DateUtils.getCurrentTime().take(5)
                )
                _messages.value = _messages.value + errorMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
}