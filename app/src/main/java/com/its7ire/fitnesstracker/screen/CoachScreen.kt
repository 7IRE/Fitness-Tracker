package com.its7ire.fitnesstracker.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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


@Preview
@Composable
fun CoachScreen() {

    var input by rememberSaveable {
        mutableStateOf("")
    }
    var isTyping by remember {
        mutableStateOf(false)
    }

    val messages = remember {
        mutableStateListOf(
            CoachChatMessageData(
                message = "...",
                isUser = false,
                time = "09:30"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CoachTopBar()
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

            verticalArrangement = Arrangement.spacedBy(12.dp),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 16.dp
            )
        ) {

            item {
                GreetingCard()
            }

            if (messages.size == 1) {
                item {
                    EmptyState()
                }
            } else {
                items(messages) { message ->
                    if (message.isUser) {
                        UserMessageBubble(
                            message = message
                        )
                    } else {
                        CoachMessageBubble(
                            message = message
                        )
                    }
                }
                if (isTyping) {
                    item {
                        TypingIndicator()
                    }
                }
            }

            item {
                SuggestionChips(
                    onChipClick = { suggestion ->

<<<<<<< Updated upstream
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

=======
>>>>>>> Stashed changes
                        messages.add(

                            CoachChatMessageData(
<<<<<<< Updated upstream

                                message = input,

=======
                                message = suggestion,
>>>>>>> Stashed changes
                                isUser = true,

                                time = "Now"

                            )

                        )

                        input = ""

                    }
<<<<<<< Updated upstream

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

=======
                )
            }
        }

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
>>>>>>> Stashed changes
    }

}