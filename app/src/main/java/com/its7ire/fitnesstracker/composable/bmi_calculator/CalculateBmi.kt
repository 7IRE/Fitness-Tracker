package com.its7ire.fitnesstracker.composable.bmi_calculator

import android.R.attr.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontVariation.weight

@Composable
fun Calculatebmi(Weight: Double, Height: Double): Double {
    return Weight / (height * height)
}