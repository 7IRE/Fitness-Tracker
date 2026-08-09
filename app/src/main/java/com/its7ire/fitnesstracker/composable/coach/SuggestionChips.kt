package com.its7ire.fitnesstracker.composable.coach

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SuggestionChips(
    onChipClick: (String) -> Unit = {}
) {

    val suggestions = listOf(
        "Plan Workout",
        "Gain Muscle",
        "Lose Fat",
        "Recovery Tips",
        "High Protein Meals",
        "Stretching",
        "Home Workout",
        "Improve Stamina"
    )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        suggestions.forEach { suggestion ->

            AssistChip(
                onClick = {
                    onChipClick(suggestion)
                },
                label = {
                    Text(text = suggestion)
                },
                shape = RoundedCornerShape(50),
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    labelColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}