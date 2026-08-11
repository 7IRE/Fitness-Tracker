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
    strokeWidth: Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "ArcProgress"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        val strokeWidthPx = strokeWidth.toPx()

        val width = size.width - strokeWidthPx
        val height = size.height * 1.8f

        val startAngle = 210f
        val sweepAngle = 120f

        val topOffset = strokeWidthPx / 2f
        val leftOffset = strokeWidthPx / 2f

        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = Offset(leftOffset, topOffset),
            size = Size(width, height)
        )

        drawArc(
            brush = Brush.sweepGradient(
                colors = gradientColors,
                center = Offset(size.width / 2f, height / 2f)
            ),
            startAngle = startAngle,
            sweepAngle = sweepAngle * animatedProgress,
            useCenter = false,
            style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
            topLeft = Offset(leftOffset, topOffset),
            size = Size(width, height)
        )
    }
}