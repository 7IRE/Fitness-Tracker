package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeBMICard(
    index: Double,
    modifier: Modifier = Modifier,
    onClick: () -> Unit ={}
) {
    HomeStatCard(
        icon = Icons.Filled.Speed,
        iconTint = MaterialTheme.colorScheme.primary,
        label = "BMI",
        value = index.toString(),
        unit = "Body Mass Index ",
        modifier = modifier
    )
}