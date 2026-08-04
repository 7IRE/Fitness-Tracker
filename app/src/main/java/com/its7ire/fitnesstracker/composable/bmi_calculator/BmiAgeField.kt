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
fun BmiAgeField(
    modifier: Modifier = Modifier
) {
    val age = remember { mutableStateOf("") }

    TextField(
        value = age.value,
        onValueChange = { age.value = it },
        singleLine = true,
        label = { Text("Age") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}
