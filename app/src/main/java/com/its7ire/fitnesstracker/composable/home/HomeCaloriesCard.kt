package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeCaloriesCard(kcal: Int, modifier: Modifier = Modifier) {
    HomeStatCard(
        icon = Icons.Filled.LocalFireDepartment,
        iconTint = Color(0xFFFF6B4A),
        label = "Calories",
        value = kcal.toString(),
        unit = "kcal burned",
        modifier = modifier
    )
}