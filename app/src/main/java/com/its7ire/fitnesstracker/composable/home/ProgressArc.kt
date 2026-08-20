package com.its7ire.fitnesstracker.composable.home

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MultiColorArcProgressBar(
    targetProgress: Float,
    modifier: Modifier = Modifier,
    gradientColors: List<Color> = listOf(
        Color(0xFF00E5FF),
        Color(0xFF00E676),
        Color(0xFFFFEA00),
        Color(0xFFFF0000)
    ),
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    strokeWidth: Dp = 7.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "ArcProgress"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
    ) {
        val strokeWidthPx = strokeWidth.toPx()
        val availableWidth = size.width - strokeWidthPx
        val availableHeight = size.height - strokeWidthPx
        val diameter = minOf(availableWidth, availableHeight * 2f).coerceAtLeast(0f)
        val radius = diameter / 2f
        val left = (size.width - diameter) / 2f
        val top = (size.height - radius - strokeWidthPx / 2f).coerceAtLeast(strokeWidthPx / 2f)

        val startAngle = 180f
        val sweepAngle = 180f

        // Track Arc (Semi-circle)
        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = Offset(left, top),
            size = Size(diameter, diameter)
        )

        // Progress Arc
        if (animatedProgress > 0f) {
            drawArc(
                brush = Brush.horizontalGradient(
                    colors = gradientColors,
                    startX = left,
                    endX = left + diameter
                ),
                startAngle = startAngle,
                sweepAngle = sweepAngle * animatedProgress,
                useCenter = false,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
                topLeft = Offset(left, top),
                size = Size(diameter, diameter)
            )
        }
    }
}