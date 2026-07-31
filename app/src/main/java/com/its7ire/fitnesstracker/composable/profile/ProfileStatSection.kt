package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileStatSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ProfileStatCard(
            title = "STREAK",
            value = "14",
            suffix = "days",
            icon = Icons.Default.LocalFireDepartment,
            modifier = Modifier.weight(1f)
        )

        ProfileStatCard(
            title = "TOTAL VOL.",
            value = "12",
            suffix = "k",
            icon = Icons.Default.WorkOutline,
            modifier = Modifier.weight(1f)
        )
    }
}