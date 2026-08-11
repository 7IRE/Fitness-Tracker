package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.data.stepdata.StepsEntity
import com.its7ire.fitnesstracker.utils.DateUtils.getCurrentDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StepHistoryViewModel(
    private val repository: StepRepository
) : ViewModel() {

    val history: Flow<List<StepsEntity>> = repository.history

    fun saveStepUpdate(stepCount: Int) {
        viewModelScope.launch {
            repository.save(
                StepsEntity(
                    steps = stepCount,
                    timestamp= System.currentTimeMillis(),
                    day = getCurrentDate(),
                    sensorStart = 0
                )
            )
        }
    }
}