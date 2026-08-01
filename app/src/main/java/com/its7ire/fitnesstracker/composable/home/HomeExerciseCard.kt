package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeExerciseCard(
    minutes: Int,
    modifier: Modifier = Modifier
) {
    HomeStatCard(
        icon = Icons.Filled.Timer,
        iconTint = MaterialTheme.colorScheme.primary,
        label = "Exercise",
        value = minutes.toString(),
        unit = "min active",
        modifier = modifier
    )
}