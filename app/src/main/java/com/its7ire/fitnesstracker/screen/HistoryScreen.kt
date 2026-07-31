 package com.its7ire.fitnesstracker.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.composable.history.HistoryAvgCard
import com.its7ire.fitnesstracker.composable.history.HistoryDailyLogCard
import com.its7ire.fitnesstracker.composable.history.HistoryPrevWeekButton
import com.its7ire.fitnesstracker.composable.history.HistoryStepSection
import com.its7ire.fitnesstracker.composable.history.HistoryTopBar

 private val BgBlack = Color(0xFF0A0A0A)

val CardDarker = Color(0xFF151513)
val Lime = Color(0xFFC6F135)
val TextWhite = Color(0xFFF5F5F0)
val OrangeMuted = Color(0xFFC9946B)
val BarInactive = Color(0xFF2A2A26)

data class DailyLogEntry(
    val dayLabel: String,
    val date: String,
    val steps: Int,
    val statusText: String,
    val goalReached: Boolean,
    val isToday: Boolean = false
)

data class BarData(val label: String, val heightFraction: Float, val isHighlighted: Boolean)


@Composable
fun PerformanceScreen(
    weeklySteps: String = "68,420",
    weeklyChangePercent: String = "+12%",
    dailyAverage: String = "774",
    dailyAverageBadge: String = "12,450",
    bars: List<BarData> = defaultBars(),
    logEntries: List<DailyLogEntry> = defaultLogEntries(),
    onLoadPreviousWeek: () -> Unit = {}
) {
    Scaffold(
        containerColor = BgBlack,
        bottomBar = {        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgBlack)
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(12.dp))
            HistoryTopBar()
            Spacer(Modifier.height(24.dp))
            HistoryStepSection(weeklySteps, weeklyChangePercent)
            Spacer(Modifier.height(20.dp))
            HistoryAvgCard(dailyAverage, dailyAverageBadge, bars)
            Spacer(Modifier.height(28.dp))
            Text(
                text = "DAILY LOG",
                color = OrangeMuted,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(12.dp))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                logEntries.forEach { entry -> HistoryDailyLogCard(entry) }
            }
            Spacer(Modifier.height(16.dp))
            HistoryPrevWeekButton(onLoadPreviousWeek)
            Spacer(Modifier.height(12.dp))
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

