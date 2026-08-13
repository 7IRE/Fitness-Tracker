package com.its7ire.fitnesstracker.composable.coach

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ApiKeyPopup(
    onKeySaved: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var keyInput by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Enter Gemini API Key") },
        text = {
            Column {
                Text("To use the AI Coach, please provide your personal Gemini API Key.")
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = keyInput,
                    onValueChange = {
                        keyInput = it
                        isError = false
                    },
                    label = { Text("API Key") },
                    singleLine = true,
                    isError = isError,
                    supportingText = { if (isError) Text("Key cannot be empty") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (keyInput.isNotBlank()) {
                        onKeySaved(keyInput.trim())
                    } else {
                        isError = true
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}