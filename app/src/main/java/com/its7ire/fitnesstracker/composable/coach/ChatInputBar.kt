package com.its7ire.fitnesstracker.composable.coach

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    requestFocus: Boolean = false
) {

    val focusRequester = remember {
        FocusRequester()
    }

    val keyboardController =
        LocalSoftwareKeyboardController.current

    LaunchedEffect(requestFocus) {

        if (requestFocus) {

            focusRequester.requestFocus()

            keyboardController?.show()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,

            modifier = Modifier
                .weight(1f)
                .focusRequester(focusRequester),

            placeholder = {
                Text(
                    text = "Ask your fitness coach..."
                )
            },
            shape = RoundedCornerShape(30.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor =
                    MaterialTheme.colorScheme.surfaceContainer,
                unfocusedContainerColor =
                    MaterialTheme.colorScheme.surfaceContainer,
                focusedIndicatorColor =
                    MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor =
                    MaterialTheme.colorScheme.outlineVariant
            )
        )

        Card(
            onClick = onSend,
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                modifier = Modifier.padding(16.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}