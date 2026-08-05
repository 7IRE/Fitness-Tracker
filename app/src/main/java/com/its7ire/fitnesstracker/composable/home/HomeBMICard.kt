package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    HomeStatCard(
        icon = Icons.Filled.Speed,
        iconTint = MaterialTheme.colorScheme.primary,
        label = "BMI",
        value = formattedValue,
        unit = "Body Mass Index",
        onClick = onClick,
        modifier = modifier
    )
}