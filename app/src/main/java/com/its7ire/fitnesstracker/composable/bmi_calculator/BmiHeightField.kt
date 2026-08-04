package com.its7ire.fitnesstracker.composable.bmi_calculator

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun BmiHeightField(
    modifier: Modifier = Modifier
) {
    val height = remember { mutableStateOf("") }

    TextField(
        value = height.value,
        onValueChange = { height.value = it },
        singleLine = true,
        label = { Text("Height (Meter)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}
