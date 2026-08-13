package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.coach.GeminiRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CoachViewModel : ViewModel() {

    private val repository = GeminiRepo()

    private val _response = MutableStateFlow("")
    val response = _response.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // 👈 Add apiKey as a parameter here instead
    fun askCoach(question: String, apiKey: String?) {

        if (question.isBlank()) return

        viewModelScope.launch {

            _isLoading.value = true

            try {

                if (apiKey.isNullOrBlank()) {
                    _response.value = "API Key is missing. Please enter it in settings."
                    return@launch
                }

                val answer = repository.askCoach(question, apiKey)

                _response.value = answer

            } catch (e: Exception) {
                _response.value = "Sorry, something went wrong."
            } finally {
                _isLoading.value = false
            }
        }
    }
}