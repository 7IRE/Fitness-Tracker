package com.its7ire.fitnesstracker.composable.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

fun calculateDistanceWalked(
    steps: Int,
    strideLengthMeters: Double = 0.75
): Double {

    return (steps * strideLengthMeters) / 1000.0
}

fun calculateDistanceProgress(
    distanceKm: Double
): Float {

    val progress = (distanceKm / 10.0).toFloat()

    return progress.coerceIn(0f, 1f)
}

data class DistanceCategory(
    val label: String,
    val color: Color
)

fun getDistanceCategory(
    distance: Double?
): DistanceCategory {

    if (distance == null) {
        return DistanceCategory(
            label = "No Data",
            color = Color(0xFF9E9E9E)
        )
    }

    return when {
        distance < 1.0 -> DistanceCategory("Very Low", Color(0xFF3300FF)) // Red
        distance < 2.5 -> DistanceCategory("Low", Color(0xFF30AAFF))          // Orange
        distance < 5.0 -> DistanceCategory("Normal", Color(0xFF00E676))        // Green
        distance < 7.5 -> DistanceCategory("Good", Color(0xFFFFEA00))            // Yellow
        distance < 10.0 -> DistanceCategory("Very Good", Color(0xFFFF9100))        // Orange
        else -> DistanceCategory("Excellent", Color(0xFFFF1744))              // Bright Red
    }
}

@Composable
fun HomeDistanceCard(
    steps: Int,
    modifier: Modifier = Modifier
) {

    val distance = calculateDistanceWalked(steps)

    val formattedDistance =
        if (distance > 0) {
            "%.2f".format(distance)
        } else {
            "--"
        }

    val distanceCategory =
        getDistanceCategory(distance)

    HomeStatCard(
        icon = Icons.Filled.LocationOn,
        iconTint = MaterialTheme.colorScheme.primary,
        label = "Distance",
        value = formattedDistance,
        unit = "km",
        onClick = {},
        targetProgress = calculateDistanceProgress(distance),
        bottomTextColor = distanceCategory.color,
        bottomTextValue = distanceCategory.label,
        modifier = modifier.fillMaxSize()
    )
}