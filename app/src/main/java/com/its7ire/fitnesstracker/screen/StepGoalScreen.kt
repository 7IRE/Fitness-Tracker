package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.its7ire.fitnesstracker.composable.stepgoal.StepGoalField
import com.its7ire.fitnesstracker.ui.theme.AppTheme


@Composable
fun StepGoalScreenContent() {

    var selectedGoal by remember { mutableIntStateOf(10000) }

    Surface {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            StepGoalField(
                onGoalSelected = { goal ->

                    selectedGoal = goal

                }
            )
        }
    }
}
@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun BMIScreenPreview() {
    AppTheme(dynamicColor = false) {
        StepGoalScreenContent()
    }
}