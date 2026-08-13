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

    fun askCoach(question: String) {

        if (question.isBlank()) return

        viewModelScope.launch {

            _isLoading.value = true

            try {

                val answer = repository.askCoach(question)

                _response.value = answer

            } catch (e: Exception) {

                _response.value =
                    "Sorry, something went wrong."

            } finally {

                _isLoading.value = false
            }
        }
    }
}