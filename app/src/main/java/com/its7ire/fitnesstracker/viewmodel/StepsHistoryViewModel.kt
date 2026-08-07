package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.StepRepository
import com.its7ire.fitnesstracker.data.StepsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StepHistoryViewModel(
    private val repository: StepRepository
) : ViewModel() {

    // Observe all step history
    val history: Flow<List<StepsEntity>> = repository.history

    // Save a new sensor update
    fun saveStepUpdate(stepCount: Int) {
        viewModelScope.launch {
            repository.save(
                StepsEntity(
                    steps = stepCount,
                    timestamp= System.currentTimeMillis(),
                    day = "Monday"
                )
            )
        }
    }
}