package com.its7ire.fitnesstracker.Screen

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- Color palette ----------
private val BgBlack = Color(0xFF0A0A0A)
private val CardDark = Color(0xFF1A1A18)
private val CardDarker = Color(0xFF151513)
private val AccentLime = Color(0xFFC6F135)
private val TextGray = Color(0xFF8A8A85)
private val TextWhite = Color(0xFFF5F5F0)
private val OrangeMuted = Color(0xFFC9946B)
private val BarInactive = Color(0xFF2A2A26)

// ---------- Data models ----------
data class DailyLogEntry(
    val dayLabel: String,
    val date: String,
    val steps: Int,
    val statusText: String,
    val goalReached: Boolean,
    val isToday: Boolean = false
)

data class BarData(val label: String, val heightFraction: Float, val isHighlighted: Boolean)

// ---------- Screen ----------
@Preview
@Composable
fun PerformanceScreen(
    weeklySteps: String = "68,420",
    weeklyChangePercent: String = "+12%",
    dailyAverage: String = "774",
    dailyAverageBadge: String = "12,450",
    bars: List<BarData> = defaultBars(),
    logEntries: List<DailyLogEntry> = defaultLogEntries(),
    onLoadPreviousWeek: () -> Unit = {},
    selectedTab: BottomTab = BottomTab.HISTORY,
    onTabSelected: (BottomTab) -> Unit = {}
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
            TopBar()
            Spacer(Modifier.height(24.dp))
            WeeklyStepsSection(weeklySteps, weeklyChangePercent)
            Spacer(Modifier.height(20.dp))
            DailyAverageCard(dailyAverage, dailyAverageBadge, bars)
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
                logEntries.forEach { entry -> DailyLogCard(entry) }
            }
            Spacer(Modifier.height(16.dp))
            LoadPreviousWeekButton(onLoadPreviousWeek)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(CardDark),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile",
                tint = TextGray,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = "Performance",
            color = AccentLime,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Settings",
            tint = TextWhite,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun WeeklyStepsSection(steps: String, changePercent: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "THIS WEEK",
            color = OrangeMuted,
            fontSize = 12.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = steps,
                color = TextWhite,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = "steps",
                color = AccentLime,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(CardDark)
                .padding(horizontal = 14.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.TrendingUp,
                contentDescription = null,
                tint = AccentLime,
                modifier = Modifier.size(14.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "$changePercent vs last week",
                color = AccentLime,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun DailyAverageCard(average: String, badge: String, bars: List<BarData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardDark)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Daily Average",
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF232320))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(text = badge, color = TextWhite, fontSize = 12.sp)
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = average,
                    color = TextWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        BarChart(bars)
    }
}

@Composable
private fun BarChart(bars: List<BarData>) {
    val maxBarHeight = 110.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxBarHeight + 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEach { bar ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.height(maxBarHeight + 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(maxBarHeight * bar.heightFraction)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (bar.isHighlighted) AccentLime else BarInactive)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = bar.label,
                    color = if (bar.isHighlighted) TextWhite else TextGray,
                    fontSize = 12.sp,
                    fontWeight = if (bar.isHighlighted) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
private fun DailyLogCard(entry: DailyLogEntry) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(CardDarker)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(
                    Brush.sweepGradient(
                        listOf(AccentLime, AccentLime, Color(0xFF2A2A26), Color(0xFF2A2A26))
                    )
                )
                .padding(3.dp)
                .clip(CircleShape)
                .background(CardDarker),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.DirectionsWalk,
                contentDescription = null,
                tint = TextWhite,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = entry.dayLabel,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = entry.date,
                color = TextGray,
                fontSize = 13.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "%,d".format(entry.steps),
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = entry.statusText,
                color = if (entry.goalReached) AccentLime else TextGray,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun LoadPreviousWeekButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(CardDark)
            .padding(vertical = 16.dp)
            .then(Modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Load Previous Week",
            color = TextWhite,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

// ---------- Bottom navigation ----------
enum class BottomTab(val label: String) {
    DASHBOARD("Dashboard"),
    HISTORY("History"),
    COACH("Coach"),
    PROFILE("Profile")
}

// ---------- Sample data ----------
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

// ---------- Preview ----------
@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun PerformanceScreenPreview() {
    MaterialTheme(colorScheme = darkColorScheme(background = BgBlack)) {
        PerformanceScreen()
    }
}