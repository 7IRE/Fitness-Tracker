package com.its7ire.fitnesstracker.composable.history

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.screen.BarData

@Composable
fun HistoryAvgCard(
    average: String,
    bars: List<BarData>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Daily Average",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = average,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = "Steps",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        HistoryBarChart(bars)
    }
}

@Composable
fun HistoryBarChart(
    bars: List<BarData>,
    modifier: Modifier = Modifier
) {
    val barTrackHeight = 90.dp

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEachIndexed { index, bar ->
            val animatedFraction by animateFloatAsState(
                targetValue = bar.heightFraction.coerceIn(0f, 1f),
                animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing),
                label = "BarHeightAnimation_$index"
            )

            val stepText = when {
                bar.steps >= 10000 -> "%.0fk".format(bar.steps / 1000f)
                bar.steps >= 1000 -> "%.1fk".format(bar.steps / 1000f)
                bar.steps > 0 -> "${bar.steps}"
                else -> "-"
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stepText,
                    color = if (bar.isHighlighted) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    },
                    fontSize = 11.sp,
                    fontWeight = if (bar.isHighlighted) FontWeight.Bold else FontWeight.Normal
                )

                Spacer(Modifier.height(6.dp))

                Box(
                    modifier = Modifier
                        .width(22.dp)
                        .height(barTrackHeight)
                        .clip(RoundedCornerShape(11.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val minHeightFraction = if (bar.steps > 0) 0.08f else 0f
                    val effectiveFraction = maxOf(animatedFraction, minHeightFraction)

                    if (effectiveFraction > 0f) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(effectiveFraction)
                                .clip(RoundedCornerShape(11.dp))
                                .background(
                                    if (bar.isHighlighted) {
                                        Brush.verticalGradient(
                                            listOf(
                                                MaterialTheme.colorScheme.primary,
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)
                                            )
                                        )
                                    } else {
                                        Brush.verticalGradient(
                                            listOf(
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                                                MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                                            )
                                        )
                                    }
                                )
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = bar.label,
                    color = if (bar.isHighlighted) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    fontSize = 12.sp,
                    fontWeight = if (bar.isHighlighted) FontWeight.Bold else FontWeight.Medium
                )

                Spacer(Modifier.height(3.dp))

                if (bar.isHighlighted) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                } else {
                    Spacer(Modifier.size(4.dp))
                }
            }
        }
    }
}