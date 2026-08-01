package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.its7ire.fitnesstracker.composable.coach.CoachChatMessageData
import com.its7ire.fitnesstracker.composable.coach.ChatInputBar
import com.its7ire.fitnesstracker.composable.coach.CoachMessageBubble
import com.its7ire.fitnesstracker.composable.coach.CoachTopBar
import com.its7ire.fitnesstracker.composable.coach.EmptyState
import com.its7ire.fitnesstracker.composable.coach.GreetingCard
import com.its7ire.fitnesstracker.composable.coach.SuggestionChips
import com.its7ire.fitnesstracker.composable.coach.TypingIndicator
import com.its7ire.fitnesstracker.composable.coach.UserMessageBubble
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun CoachScreen() {

    var input by rememberSaveable {
        mutableStateOf("")
    }

    var isTyping by remember {
        mutableStateOf(false)
    }

    val messages = remember {
        mutableStateListOf<CoachChatMessageData>(
            CoachChatMessageData(
                message = "...",
                isUser = false,
                time = "09:30"
            )
        )
    }



    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            ChatInputBar(
                value = input,
                onValueChange = {
                    input = it
                },
                onSend = {
                    if (input.isNotBlank()) {
                        messages.add(
                            CoachChatMessageData(
                                message = input,
                                isUser = true,
                                time = "Now"
                            )
                        )
                        input = ""
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CoachTopBar()
            Spacer(modifier = Modifier.height(20.dp))
            GreetingCard()
            Spacer(modifier = Modifier.height(20.dp))
            if (messages.size == 1) {
                EmptyState()
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(messages) { message ->
                        if (message.isUser) {
                            UserMessageBubble(message)
                        } else {
                            CoachMessageBubble(message)
                        }
                    }
                    if (isTyping) {
                        item {
                            TypingIndicator()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            SuggestionChips(
                onChipClick = {
                    input = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PerformanceScreenPreview() {
    AppTheme(dynamicColor = false) {
        CoachScreen()
    }
}