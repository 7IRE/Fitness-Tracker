package com.its7ire.fitnesstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.screen.BarData
import com.its7ire.fitnesstracker.screen.DailyLogEntry
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class PerformanceUiState(
    val weeklySteps: Int = 0,
    val dailyAverage: Int = 0,
    val changePercent: String = "0%",
    val bars: List<BarData> = emptyList(),
    val logs: List<DailyLogEntry> = emptyList()
)

class PerformanceViewModel(
    private val repository: StepRepository
) : ViewModel() {

    var uiState by mutableStateOf(PerformanceUiState())
        private set

    init {
        loadWeek()
    }

    private fun loadWeek() {

        val start = getStartOfWeek()
        val end = getEndOfWeek()

        viewModelScope.launch {

            repository.getWeeklySteps(start, end)
                .collect { list ->

                    val grouped = list
                        .groupBy { it.day }
                        .mapValues { entry ->
                            entry.value.maxOf { it.steps }
                        }
                    val bars = createBars(grouped)

                    val logs = createLogs(grouped)

                    val total = grouped.values.sum()

                    uiState = PerformanceUiState(
                        weeklySteps = total,
                        dailyAverage = total / 7,
                        changePercent = "0%",
                        bars = bars,
                        logs = logs
                    )
                }
        }
    }

    fun getStartOfWeek(): Long {

        val cal = Calendar.getInstance()

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

        return cal.timeInMillis
    }

    fun getEndOfWeek(): Long {

        val cal = Calendar.getInstance()

        cal.timeInMillis = getStartOfWeek()

        cal.add(Calendar.DAY_OF_MONTH, 7)

        return cal.timeInMillis - 1
    }

    private fun createBars(
        data: Map<String, Int>
    ): List<BarData> {

        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )

        val dayFormat = SimpleDateFormat(
            "EEE",
            Locale.getDefault()
        )

        val calendar = Calendar.getInstance()

        // Monday
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        val dates = mutableListOf<Pair<String, String>>()

        repeat(7) {

            val dateKey = dateFormat.format(calendar.time)
            val dayName = dayFormat.format(calendar.time)

            dates.add(dateKey to dayName)

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        val maxSteps = (data.values.maxOrNull() ?: 1).toFloat()

        return dates.map { (dateKey, dayName) ->

            val steps = data[dateKey] ?: 0

            BarData(
                label = dayName.first().toString(),
                heightFraction = (steps / maxSteps).coerceIn(0f, 1f),
                isHighlighted = dateKey == dateFormat.format(Date())
            )
        }
    }
    private fun createLogs(
        data: Map<String, Int>
    ): List<DailyLogEntry> {

        val goal = 10_000

        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        )

        val dayFormat = SimpleDateFormat(
            "EEE",
            Locale.getDefault()
        )

        val displayDateFormat = SimpleDateFormat(
            "dd MMM",
            Locale.getDefault()
        )

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        val logs = mutableListOf<DailyLogEntry>()

        repeat(7) {

            val dateKey = dateFormat.format(calendar.time)
            val dayLabel = dayFormat.format(calendar.time)
            val displayDate = displayDateFormat.format(calendar.time)

            val steps = data[dateKey] ?: 0

            logs.add(
                DailyLogEntry(
                    dayLabel = dayLabel,
                    date = displayDate,
                    steps = steps,
                    statusText = if (steps >= goal) {
                        "Goal reached"
                    } else {
                        "${(steps * 100) / goal}% of goal"
                    },
                    goalReached = steps >= goal,
                    isToday = dateKey == dateFormat.format(Date())
                )
            )

            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return logs
    }
    private fun getTodayName(): String {
        return SimpleDateFormat(
            "EEE",
            Locale.getDefault()
        ).format(Date())
    }
}