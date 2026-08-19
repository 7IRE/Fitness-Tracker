package com.its7ire.fitnesstracker.composable.home.bmi

fun calculateBmi(
    height: String,
    weight: String
): Double? {
    val h = height.toDoubleOrNull() ?: return null
    val w = weight.toDoubleOrNull() ?: return null

    if (h <= 0.0 || w <= 0.0) return null

    val heightInMeters = h / 100.0
    return w / (heightInMeters * heightInMeters)
}