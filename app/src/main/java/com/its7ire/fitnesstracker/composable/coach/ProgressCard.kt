package com.its7ire.fitnesstracker.composable.coach

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import com.its7ire.fitnesstracker.ui.theme.neumorphic
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProgressCard(
    streak: Int,
    workouts: Int,
    steps: Int,
    improvement: Int
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .neumorphic(cornerRadius = 24.dp)

    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShowChart,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Weekly Progress",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 18.dp)
            )

            ProgressItem(
                Icons.Default.LocalFireDepartment,
                "$streak Day Streak"
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressItem(
                Icons.Default.FitnessCenter,
                "$workouts Workouts Completed"
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressItem(
                Icons.Default.DirectionsWalk,
                "$steps Steps"
            )

            Spacer(modifier = Modifier.height(14.dp))

            ProgressItem(
                Icons.Default.TrendingUp,
                "+$improvement% Compared to Last Week"
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 18.dp)
            )

            Text(
                text = "You're becoming more consistent.\nKeep it up! 💪",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }

    }

}


@Composable
fun ProgressItem(

    icon: ImageVector,

    text: String

) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )

    }

}
