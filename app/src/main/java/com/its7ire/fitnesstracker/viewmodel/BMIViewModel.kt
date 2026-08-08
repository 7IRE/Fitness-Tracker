package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.bmidata.BMIRepository
import com.its7ire.fitnesstracker.data.bmidata.BMI_Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BmiUiState(
    val height: String = "",
    val weight: String = "",
    val bmi: Double? = null,
    val isCalculated: Boolean = false
)

class BmiViewModel(
    private val repository: BMIRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BmiUiState())
    val uiState: StateFlow<BmiUiState> = _uiState.asStateFlow()

    fun onHeightChange(newHeight: String) {
        _uiState.update { it.copy(height = newHeight) }
    }

    fun onWeightChange(newWeight: String) {
        _uiState.update { it.copy(weight = newWeight) }
    }

    fun onCalculateBmi() {
        val heightVal = _uiState.value.height.toDoubleOrNull()
        val weightVal = _uiState.value.weight.toDoubleOrNull()

        val calculatedBmi = if (heightVal != null && weightVal != null && heightVal > 0) {
            val heightInMeters = heightVal / 100.0
            weightVal / (heightInMeters * heightInMeters)
        } else {
            null
        }

        _uiState.update {
            it.copy(bmi = calculatedBmi, isCalculated = true)
        }

        if (calculatedBmi != null && heightVal != null && weightVal != null) {
            saveBmi(heightVal, weightVal, calculatedBmi)
        }
    }

    private fun saveBmi(height: Double, weight: Double, bmi: Double) {
        viewModelScope.launch {
            val profile = BMI_Data(
                age = 0, // wire up an actual age field later if needed
                height = height.toFloat(),
                weight = weight.toFloat(),
                bmi = bmi.toFloat()
            )
            repository.saveBMI(profile)
        }
    }

    init {
        loadBMI()
    }


    private fun loadBMI() {

        viewModelScope.launch {

            val savedBMI = repository.getBMI()

            if (savedBMI != null) {

                _uiState.update {
                    it.copy(
                        height = savedBMI.height.toString(),
                        weight = savedBMI.weight.toString(),
                        bmi = savedBMI.bmi.toDouble(),
                        isCalculated = true
                    )
                }
            }
        }
    }
}