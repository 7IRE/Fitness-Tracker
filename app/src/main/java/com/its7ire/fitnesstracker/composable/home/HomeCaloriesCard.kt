package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeCaloriesCard(
    kcal: Int,
    modifier: Modifier = Modifier
) {
    HomeStatCard(
        icon = Icons.Filled.LocalFireDepartment,
        iconTint = MaterialTheme.colorScheme.tertiary,
        label = "Calories",
        value = kcal.toString(),
        unit = "kcal burned",
        modifier = modifier
    )
}