package com.its7ire.fitnesstracker

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    fun currentTimestamp(): Long {
        return System.currentTimeMillis()
    }

    fun getCurrentDay(): String {
        return SimpleDateFormat(
            "EEEE",
            Locale.getDefault()
        ).format(Date())
    }

    fun getCurrentDate(): String {
        return SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date())
    }

    fun getCurrentTime(): String {
        return SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Date())
    }

    fun formatDate(timestamp: Long): String {
        return SimpleDateFormat(
            "dd MMM yyyy",
            Locale.getDefault()
        ).format(Date(timestamp))
    }

    fun formatDay(timestamp: Long): String {
        return SimpleDateFormat(
            "EEEE",
            Locale.getDefault()
        ).format(Date(timestamp))
    }
    fun getCurrentDateShort(): String {
        return SimpleDateFormat(
            "EEE, MMM dd",
            Locale.getDefault()
        ).format(Date())
    }

    fun formatTime(timestamp: Long): String {
        return SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Date(timestamp))
    }

    fun formatDateTime(timestamp: Long): String {
        return SimpleDateFormat(
            "dd MMM yyyy  HH:mm:ss",
            Locale.getDefault()
        ).format(Date(timestamp))
    }

    fun getGreeting(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..21 -> "Good Evening"
            else -> "Good Night"
        }
    }
}