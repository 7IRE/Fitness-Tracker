package com.its7ire.fitnesstracker.composable.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class CalorieStatus(
    val label: String,
    val color: Color
)

fun getCalorieStatus(kcal: Int?): CalorieStatus {
    if (kcal == null || kcal <= 0) {
        return CalorieStatus(
            label = "Just Started",
            color = Color(0xFF9E9E9E)
        )
    }

    return when {
        kcal < 300  -> CalorieStatus(
            label = "Light Activity",
            color = Color(0xFF00E5FF)
        )
        kcal < 600  -> CalorieStatus(
            label = "Moderate Burn",
            color = Color(0xFF00E676)
        )
        kcal < 1000 -> CalorieStatus(
            label = "Active Day",
            color = Color(0xFFFFEA00)
        )
        kcal < 1500 -> CalorieStatus(
            label = "High Energy",
            color = Color(0xFFFF9100)
        )
        else        -> CalorieStatus(
            label = "Intense Burn",
            color = Color(0xFFFF1744)
        )
    }
}
fun calculateCalorieProgress(calories: Int): Float {
    if (calories <= 0) return 0f
    val progress = (calories / 1000.0).toFloat()
    return progress.coerceIn(0f, 1f)
}
@Composable
fun HomeCaloriesCard(
    kcal: Int,
    modifier: Modifier = Modifier
) {
    val status = getCalorieStatus(kcal)
    HomeStatCard(
        icon = Icons.Filled.LocalFireDepartment,
        iconTint = MaterialTheme.colorScheme.tertiary,
        label = "Calories",
        value = kcal.toString(),
        unit = "kcal burned",
        targetProgress = calculateCalorieProgress(kcal),
        bottomTextColor = status.color,
        bottomTextValue = status.label,
        modifier = modifier.fillMaxWidth()
    )
}