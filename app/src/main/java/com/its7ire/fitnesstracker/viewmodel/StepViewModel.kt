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
    private var startSensorValue = -1

    fun processSensorValue(currentSensorValue: Int) {
        viewModelScope.launch {
            val today = getCurrentDate()
            if (startSensorValue == -1) {
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
                    return@launch
                } else {
                    startSensorValue = lastRecord.sensorStart
                }
            }
            if (currentSensorValue < startSensorValue) {
                startSensorValue = 0
            }
            val todaySteps = currentSensorValue - startSensorValue
            _steps.value = todaySteps
            if (todaySteps > 0 && todaySteps % 10 == 0) {
                saveTodaySteps()
            }
        }
    }

    fun saveTodaySteps() {
        viewModelScope.launch {
            val lastRecord = repository.getLastStep()
            if (lastRecord != null) {
                repository.updateSteps(lastRecord.id, _steps.value)
            }
        }
    }
}