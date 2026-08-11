package com.its7ire.fitnesstracker.composable.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


fun calculateBmiProgress(bmi: Double?): Float {
    if (bmi == null) return 0f

    val progress = ((bmi - 15.0) / 25.0).toFloat()

    return progress.coerceIn(0f, 1f)
}

data class BmiCategory(
    val label: String,
    val color: Color
)

fun getBmiCategory(index: Double?): BmiCategory {
    if (index == null) {
        return BmiCategory(label = "No Data", color = Color(0xFF9E9E9E)) // Grey
    }

    return when {
        index < 16.0 -> BmiCategory("Severely Underweight", Color(0xFF3300FF)) // Red
        index < 18.5 -> BmiCategory("Underweight", Color(0xFF30AAFF))          // Orange
        index < 25.0 -> BmiCategory("Normal Weight", Color(0xFF00E676))        // Green
        index < 30.0 -> BmiCategory("Overweight", Color(0xFFFFEA00))            // Yellow
        index < 35.0 -> BmiCategory("Obese Class I", Color(0xFFFF9100))        // Orange
        index < 40.0 -> BmiCategory("Obese Class II", Color(0xFFFF3D00))       // Deep Orange
        else -> BmiCategory("Severely Obese", Color(0xFFFF1744))              // Bright Red
    }
}
@Composable
fun HomeBMICard(
    index: Double?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val formattedValue = if (index != null) {
        "%.1f".format(index)
    } else {
        "--"
    }

    val bmiCategory = getBmiCategory(index)

    HomeStatCard(
        icon = Icons.Filled.Speed,
        iconTint = MaterialTheme.colorScheme.primary,
        label = "BMI",
        value = formattedValue,
        unit = "Body Mass Index",
        onClick = onClick,
        targetProgress = calculateBmiProgress(index),
        bottomTextColor = bmiCategory.color,
        bottomTextValue = bmiCategory.label,
        modifier = modifier.fillMaxSize()
    )
}