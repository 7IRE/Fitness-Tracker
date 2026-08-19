package com.its7ire.fitnesstracker.composable.home.bmi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun BmiHeightField(
    height: String,
    onHeightChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = height,
        onValueChange = onHeightChange,
        singleLine = true,
        label = { Text("Height") },
        placeholder = { Text("e.g. 175") },
        trailingIcon = { Text("cm", color = MaterialTheme.colorScheme.onSurfaceVariant) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Height,
                contentDescription = "Height icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
        ),
        modifier = modifier.fillMaxWidth()
    )
}

