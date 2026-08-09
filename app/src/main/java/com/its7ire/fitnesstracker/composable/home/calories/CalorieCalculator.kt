package com.its7ire.fitnesstracker.composable.home.calories

fun calculateCaloriesFromSteps(
    steps: Int,
    weightKg: Double
): Int {
    val stepLengthMeters = 0.75
    val distanceMeters = steps * stepLengthMeters

    return (distanceMeters * weightKg * 0.0005).toInt()
}