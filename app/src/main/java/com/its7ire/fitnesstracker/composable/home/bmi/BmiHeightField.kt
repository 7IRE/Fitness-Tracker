package com.its7ire.fitnesstracker.composable.home.bmi

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun BmiHeightField(
    height: String,
    onHeightChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        value = height,
        onValueChange = onHeightChange,
        singleLine = true,
        label = { Text("Height (Cm)") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )

}

