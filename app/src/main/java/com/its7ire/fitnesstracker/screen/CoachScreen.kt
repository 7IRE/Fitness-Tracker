
package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.composable.coach.ChatInputBar
import com.its7ire.fitnesstracker.composable.coach.CoachMessageBubble
import com.its7ire.fitnesstracker.composable.coach.CoachTopBar
import com.its7ire.fitnesstracker.composable.coach.EmptyState
import com.its7ire.fitnesstracker.composable.coach.GreetingCard
import com.its7ire.fitnesstracker.composable.coach.SuggestionChips
import com.its7ire.fitnesstracker.composable.coach.TypingIndicator
import com.its7ire.fitnesstracker.composable.coach.UserMessageBubble
import com.its7ire.fitnesstracker.data.userdata.UserProfile
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.CoachViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModel

@Composable
fun CoachScreen(
    user: UserProfile? = null,
    viewModel: CoachViewModel = viewModel(),
    stepViewModel: StepViewModel = viewModel()
) {
    var focusChat by remember {
        mutableStateOf(false)
    }
    var input by rememberSaveable {
        mutableStateOf("")
    }

    val messages by viewModel.messages.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    fun sendMessage(message: String) {
        if (message.isBlank()) return
        input = ""
        viewModel.askCoach(message)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 76.dp)
            ) {
                SuggestionChips(
                    onChipClick = { suggestion ->
                        sendMessage(suggestion)
                    },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                ChatInputBar(
                    value = input,
                    onValueChange = {
                        input = it
                    },
                    onSend = {
                        sendMessage(input)
                    },
                    requestFocus = focusChat
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
                    bottom = 12.dp
                )
            ) {
                item {
                    GreetingCard(
                        userName = user?.name ?: "User",
                        stepViewModel = stepViewModel
                    )
                }

                if (messages.isEmpty()) {
                    item {
                        EmptyState(
                            onStartClick = {
                                focusChat = true
                            }
                        )
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

                    if (isLoading) {
                        item {
                            TypingIndicator()
                        }
                    }
                }
            }
        }
    }
}




@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun CoachScreenPreview() {
    AppTheme {
        CoachScreen()
    }
}
