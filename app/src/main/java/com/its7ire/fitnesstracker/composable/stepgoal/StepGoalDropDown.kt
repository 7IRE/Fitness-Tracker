package com.its7ire.fitnesstracker.composable.stepgoal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepGoalField(
    modifier: Modifier = Modifier,
    onGoalSelected: (Int) -> Unit
) {

    val goals = listOf(
        5000,
        7500,
        10000,
        15000,
        20000
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedGoal by remember { mutableStateOf("10000") }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {

        TextField(
            value = "$selectedGoal steps",
            onValueChange = {},
            readOnly = true,
            label = {
                Text("Step Goal")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            goals.forEach { goal ->

                DropdownMenuItem(
                    text = {
                        Text("$goal steps")
                    },
                    onClick = {

                        selectedGoal = goal.toString()

                        onGoalSelected(goal)

                        expanded = false
                    }
                )
            }
        }
    }
}