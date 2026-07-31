package com.its7ire.fitnesstracker.composable.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.its7ire.fitnesstracker.screen.AccentLime

@Composable
fun HomeExerciseCard(minutes: Int, modifier: Modifier = Modifier) {
    HomeStatCard(
        icon = Icons.Filled.Timer,
        iconTint = AccentLime,
        label = "Exercise",
        value = minutes.toString(),
        unit = "min active",
        modifier = modifier
    )
}