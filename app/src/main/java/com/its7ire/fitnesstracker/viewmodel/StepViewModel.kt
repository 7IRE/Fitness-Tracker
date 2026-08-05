package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StepViewModel : ViewModel() {

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    fun updateSteps(stepCount: Int) {
        _steps.value = stepCount
    }

}

