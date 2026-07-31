package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileStatSection(
    modifier: Modifier = Modifier,
    streakDays: String = "14",
    totalVolumeK: String = "12"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileStatCard(
            title = "STREAK",
            value = streakDays,
            suffix = "days",
            icon = Icons.Default.LocalFireDepartment,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        ProfileStatCard(
            title = "TOTAL VOL.",
            value = totalVolumeK,
            suffix = "k",
            icon = Icons.Default.WorkOutline,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}