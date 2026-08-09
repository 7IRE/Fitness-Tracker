package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.its7ire.fitnesstracker.data.stepdata.StepRepository

class StepViewModelFactory(
    private val repository: StepRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(StepViewModel::class.java)) {
            return StepViewModel(repository) as T
        }
        
        if (modelClass.isAssignableFrom(StepHistoryViewModel::class.java)) {
            return StepHistoryViewModel(repository) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}
