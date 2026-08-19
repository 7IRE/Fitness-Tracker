package com.its7ire.fitnesstracker.composable.home.bmi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonitorWeight
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
fun BmiWeightField(
    weight: String,
    onWeightChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = weight,
        onValueChange = onWeightChange,
        singleLine = true,
        label = { Text("Weight") },
        placeholder = { Text("e.g. 70") },
        trailingIcon = { Text("kg", color = MaterialTheme.colorScheme.onSurfaceVariant) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.MonitorWeight,
                contentDescription = "Weight icon",
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