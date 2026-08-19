package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.data.stepdata.StepsEntity
import com.its7ire.fitnesstracker.DateUtils.getCurrentDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class StepViewModel(
    private val repository: StepRepository
) : ViewModel() {

    private val _steps = MutableStateFlow(0)
    val steps = _steps.asStateFlow()

    private var startSensorValue = -1
    private var baseStepsToday = 0
    private var currentRecordId: Int? = null
    private var lastSavedSteps = 0

    private val mutex = Mutex()

    init {
        loadTodaySteps()
    }

    private fun loadTodaySteps() {
        viewModelScope.launch {
            mutex.withLock {
                val today = getCurrentDate()
                val todayRecord = repository.getStepForDay(today)
                if (todayRecord != null) {
                    currentRecordId = todayRecord.id
                    baseStepsToday = 0
                    startSensorValue = todayRecord.sensorStart
                    lastSavedSteps = todayRecord.steps
                    _steps.value = todayRecord.steps
                }
            }
        }
    }

    fun processSensorValue(currentSensorValue: Int) {
        if (currentSensorValue < 0) return

        viewModelScope.launch {
            mutex.withLock {
                val today = getCurrentDate()

                val lastRecord = if (currentRecordId != null) {
                    repository.getStepForDay(today)
                } else {
                    repository.getLastStep()
                }

                if (lastRecord == null || lastRecord.day != today) {
                    // New day started or first time recording
                    startSensorValue = currentSensorValue
                    baseStepsToday = 0
                    lastSavedSteps = 0
                    _steps.value = 0

                    val newRecord = StepsEntity(
                        day = today,
                        sensorStart = currentSensorValue,
                        steps = 0,
                        timestamp = System.currentTimeMillis()
                    )
                    repository.save(newRecord)
                    currentRecordId = repository.getStepForDay(today)?.id
                    return@withLock
                }

                currentRecordId = lastRecord.id

                // Handle first sensor event in this session or phone reboot
                if (startSensorValue == -1) {
                    if (currentSensorValue >= lastRecord.sensorStart) {
                        startSensorValue = lastRecord.sensorStart
                        baseStepsToday = 0
                    } else {
                        // Phone rebooted while app was closed
                        startSensorValue = currentSensorValue
                        baseStepsToday = lastRecord.steps
                    }
                } else if (currentSensorValue < startSensorValue) {
                    // Phone rebooted during runtime session
                    baseStepsToday = _steps.value
                    startSensorValue = currentSensorValue
                }

                val delta = currentSensorValue - startSensorValue
                val todaySteps = baseStepsToday + (if (delta >= 0) delta else 0)

                _steps.value = todaySteps

                // Save periodically (every 5 steps or first time > 0)
                if (todaySteps - lastSavedSteps >= 5 || (todaySteps > 0 && lastSavedSteps == 0)) {
                    persistSteps(todaySteps)
                }
            }
        }
    }

    private suspend fun persistSteps(stepsToSave: Int) {
        val recordId = currentRecordId ?: repository.getStepForDay(getCurrentDate())?.id
        if (recordId != null) {
            repository.updateSteps(recordId, stepsToSave)
            lastSavedSteps = stepsToSave
        }
    }

    fun saveTodaySteps() {
        viewModelScope.launch {
            mutex.withLock {
                persistSteps(_steps.value)
            }
        }
    }
}