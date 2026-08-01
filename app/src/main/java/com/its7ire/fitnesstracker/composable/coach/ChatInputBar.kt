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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatInputBar(

    value: String,

    onValueChange: (String) -> Unit,

    onSend: () -> Unit

) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),

        horizontalArrangement = Arrangement.spacedBy(12.dp)

    ) {

        OutlinedTextField(

            value = value,

            onValueChange = onValueChange,

            modifier = Modifier.weight(1f),

            placeholder = {

                androidx.compose.material3.Text(
                    "Ask your fitness coach..."
                )

            },

            shape = RoundedCornerShape(30.dp),

            singleLine = true,

            colors = TextFieldDefaults.colors(

                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,

                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,

                focusedIndicatorColor = MaterialTheme.colorScheme.primary,

                unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant

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