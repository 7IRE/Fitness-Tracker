package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.data.stepdata.StepsEntity
import com.its7ire.fitnesstracker.utils.DateUtils.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StepViewModel(
    private val repository: StepRepository
) : ViewModel() {

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    fun updateSteps(stepCount: Int) {
        _steps.value = stepCount
    }

    suspend fun getLastStep(): StepsEntity? {
        return repository.getLastStep()
    }

    private var startSensorValue = 0


    fun initializeSteps(currentSensorValue: Int) {

        viewModelScope.launch {

            val today = getCurrentDate()

            val lastRecord = repository.getLastStep()

            if (lastRecord == null || lastRecord.day != today) {

                startSensorValue = currentSensorValue

                repository.save(
                    StepsEntity(
                        day = today,
                        sensorStart = currentSensorValue,
                        steps = 0,
                        timestamp = System.currentTimeMillis()
                    )
                )

                _steps.value = 0

            } else {

                startSensorValue = lastRecord.sensorStart
            }
        }
    }

    fun calculateTodaySteps(currentSensorValue: Int): Int {

        return currentSensorValue - startSensorValue

    }
}