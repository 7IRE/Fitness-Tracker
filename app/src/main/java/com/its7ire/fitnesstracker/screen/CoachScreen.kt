
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.composable.coach.CoachChatMessageData
import com.its7ire.fitnesstracker.composable.coach.ChatInputBar
import com.its7ire.fitnesstracker.composable.coach.CoachMessageBubble
import com.its7ire.fitnesstracker.composable.coach.CoachTopBar
import com.its7ire.fitnesstracker.composable.coach.EmptyState
import com.its7ire.fitnesstracker.composable.coach.GreetingCard
import com.its7ire.fitnesstracker.composable.coach.TypingIndicator
import com.its7ire.fitnesstracker.composable.coach.UserMessageBubble
import com.its7ire.fitnesstracker.data.stepdata.DatabaseProvider
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.StepViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModelFactory

@Composable
fun CoachScreen()
{
    val context = LocalContext.current


    val database = remember {
        DatabaseProvider.getDatabase(context)
    }

    val repository = remember {
        StepRepository(database.stepDao())
    }

    val stepViewModel: StepViewModel = viewModel(
        factory = StepViewModelFactory(repository)
    )



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
                    GreetingCard(stepViewModel = stepViewModel)
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
