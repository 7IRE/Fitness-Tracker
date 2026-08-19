package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.composable.history.HistoryAvgCard
import com.its7ire.fitnesstracker.composable.history.HistoryDailyLogCard
import com.its7ire.fitnesstracker.composable.history.HistoryPrevWeekButton
import com.its7ire.fitnesstracker.composable.history.HistoryStepSection
import com.its7ire.fitnesstracker.composable.history.HistoryTopBar
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.PerformanceUiState
import com.its7ire.fitnesstracker.viewmodel.PerformanceViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

data class DailyLogEntry(
    val dayLabel: String,
    val date: String,
    val steps: Int,
    val statusText: String,
    val goalReached: Boolean,
    val isToday: Boolean = false
)

data class BarData(
    val label: String,
    val heightFraction: Float,
    val isHighlighted: Boolean
)

@Composable
fun PerformanceScreen1(
    viewModel: PerformanceViewModel,
    onLoadPreviousWeek: () -> Unit = {}
) {
    val uiState = viewModel.uiState

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(12.dp))
            HistoryTopBar()

            Spacer(Modifier.height(24.dp))
            HistoryStepSection(
                uiState.weeklySteps.toString(),
                uiState.changePercent
            )

            Spacer(Modifier.height(20.dp))
            HistoryAvgCard(
                uiState.dailyAverage.toString(),
                uiState.weeklySteps.toString(),
                uiState.bars
            )

            Spacer(Modifier.height(28.dp))
            Text(
                text = "DAILY LOG",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )

            Spacer(Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                uiState.logs.forEach { entry ->
                    HistoryDailyLogCard(entry)
                }
            }

            Spacer(Modifier.height(10.dp))
            HistoryPrevWeekButton(onLoadPreviousWeek)
            Spacer(Modifier.height(90.dp))
        }
    }
}

private fun defaultBars(): List<BarData> = listOf(
    BarData("M", 0.35f, false),
    BarData("T", 0.55f, false),
    BarData("W", 0.42f, false),
    BarData("T", 0.30f, false),
    BarData("F", 0.20f, false),
    BarData("S", 1.0f, true),
    BarData("S", 0.05f, false)
)

private fun defaultLogEntries(): List<DailyLogEntry> = listOf(
    DailyLogEntry("Today", "Sat, Oct 14", 12450, "Goal reached", goalReached = true, isToday = true),
    DailyLogEntry("Yesterday", "Fri, Oct 13", 6230, "62% of goal", goalReached = false),
    DailyLogEntry("Thursday", "Thu, Oct 12", 10500, "Goal reached", goalReached = true),
    DailyLogEntry("Wednesday", "Wed, Oct 11", 7840, "78% of goal", goalReached = false)
)

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PerformanceScreenPreview() {
    AppTheme {
        PerformanceScreenPreviewContent()
    }
}

@Composable
private fun PerformanceScreenPreviewContent() {

    val previewState = PerformanceUiState(
        weeklySteps = 68420,
        dailyAverage = 9774,
        changePercent = "+12%",
        bars = listOf(
            BarData("M", 0.35f, false),
            BarData("T", 0.55f, false),
            BarData("W", 0.42f, false),
            BarData("T", 0.30f, false),
            BarData("F", 0.20f, false),
            BarData("S", 1.0f, true),
            BarData("S", 0.05f, false)
        ),
        logs = emptyList()
    )
}