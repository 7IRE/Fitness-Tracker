package com.its7ire.fitnesstracker.viewmodel
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class BmiUiState(
    val height: String = "",
    val weight: String = "",
    val bmi: Double? = null,
    val isCalculated: Boolean = false
)

class BmiViewModel : ViewModel() {

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
            it.copy(
                bmi = calculatedBmi,
                isCalculated = true
            )
        }
    }
}