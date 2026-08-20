package com.its7ire.fitnesstracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.screen.BarData
import com.its7ire.fitnesstracker.screen.DailyLogEntry
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class HistoryUiState(
    val weeklySteps: Int = 0,
    val dailyAverage: Int = 0,
    val changePercent: String = "+12%",
    val bars: List<BarData> = emptyList(),
    val logs: List<DailyLogEntry> = emptyList(),
    val loadedWeeksCount: Int = 1
)

class HistoryViewModel(
    private val repository: StepRepository
) : ViewModel() {

    var uiState by mutableStateOf(HistoryUiState())
        private set

    private var historyJob: Job? = null

    init {
        loadHistory(1)
    }

    fun loadPreviousWeek() {
        val nextWeeksCount = uiState.loadedWeeksCount + 1
        loadHistory(nextWeeksCount)
    }

    fun loadHistory(weeksCount: Int) {
        historyJob?.cancel()

        historyJob = viewModelScope.launch {
            repository.history.collectLatest { list ->
                val grouped = list
                    .groupBy { it.day }
                    .mapValues { entry -> entry.value.maxOf { it.steps } }

                val currentWeekBars = createCurrentWeekBars(grouped)
                val currentWeekSteps = currentWeekBars.sumOf { it.steps }
                val currentWeekAverage = if (currentWeekSteps > 0) currentWeekSteps / 7 else 0

                val expandedLogs = createExpandedLogs(weeksCount, grouped)

                uiState = uiState.copy(
                    weeklySteps = currentWeekSteps,
                    dailyAverage = currentWeekAverage,
                    changePercent = "+12%",
                    bars = currentWeekBars,
                    logs = expandedLogs,
                    loadedWeeksCount = weeksCount
                )
            }
        }
    }

    private fun createCurrentWeekBars(data: Map<String, Int>): List<BarData> {
        val dbDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val dayNameFormat = SimpleDateFormat("EEE", Locale.getDefault())
        val dayNumberFormat = SimpleDateFormat("d", Locale.getDefault())
        val todayStr = dbDateFormat.format(Date())

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            val dayOfWeek = get(Calendar.DAY_OF_WEEK)
            val daysFromMonday = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - Calendar.MONDAY
            add(Calendar.DAY_OF_MONTH, -daysFromMonday)
        }

        val dates = mutableListOf<Triple<String, String, String>>()
        repeat(7) {
            val dateKey = dbDateFormat.format(calendar.time)
            val dayName = dayNameFormat.format(calendar.time)
            val dayNum = dayNumberFormat.format(calendar.time)
            dates.add(Triple(dateKey, dayName, dayNum))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        val maxSteps = maxOf(data.values.maxOrNull()?.toFloat() ?: 0f, 10000f)

        return dates.map { (dateKey, dayName, dayNum) ->
            val steps = data[dateKey] ?: 0
            val isToday = (dateKey == todayStr)
            BarData(
                label = dayName.first().toString(),
                dayNumber = dayNum,
                steps = steps,
                heightFraction = if (maxSteps > 0) (steps / maxSteps).coerceIn(0f, 1f) else 0f,
                isHighlighted = isToday,
                dateKey = dateKey
            )
        }
    }

    private fun createExpandedLogs(weeksCount: Int, data: Map<String, Int>): List<DailyLogEntry> {
        val goal = 10_000
        val dbDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val dayNameFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val displayDateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())

        val now = Calendar.getInstance()
        val todayEndCalendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        val todayStr = dbDateFormat.format(now.time)

        val yesterdayCalendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }
        val yesterdayStr = dbDateFormat.format(yesterdayCalendar.time)

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            val dayOfWeek = get(Calendar.DAY_OF_WEEK)
            val daysFromMonday = if (dayOfWeek == Calendar.SUNDAY) 6 else dayOfWeek - Calendar.MONDAY
            add(Calendar.DAY_OF_MONTH, -daysFromMonday)
            add(Calendar.WEEK_OF_YEAR, -(weeksCount - 1))
        }

        val totalDays = weeksCount * 7
        val logs = mutableListOf<DailyLogEntry>()
        repeat(totalDays) {
            if (!calendar.after(todayEndCalendar)) {
                val dateKey = dbDateFormat.format(calendar.time)
                val isToday = (dateKey == todayStr)
                val isYesterday = (dateKey == yesterdayStr)
                val dayLabel = when {
                    isToday -> "Today"
                    isYesterday -> "Yesterday"
                    else -> dayNameFormat.format(calendar.time)
                }
                val displayDate = displayDateFormat.format(calendar.time)
                val steps = data[dateKey] ?: 0

                logs.add(
                    DailyLogEntry(
                        dayLabel = dayLabel,
                        date = displayDate,
                        steps = steps,
                        statusText = if (steps >= goal) "Goal reached" else "${(steps * 100) / goal}% of goal",
                        goalReached = steps >= goal,
                        isToday = isToday
                    )
                )
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return logs.reversed()
    }
}
