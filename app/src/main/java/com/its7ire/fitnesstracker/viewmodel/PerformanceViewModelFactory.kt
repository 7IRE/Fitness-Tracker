package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.its7ire.fitnesstracker.data.stepdata.StepRepository

class PerformanceViewModelFactory(
    private val repository: StepRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(PerformanceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PerformanceViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}