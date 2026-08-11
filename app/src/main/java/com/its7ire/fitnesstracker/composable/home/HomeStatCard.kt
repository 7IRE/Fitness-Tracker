package com.its7ire.fitnesstracker.composable.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun HomeStatCard(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    unit: String,
    targetProgress: Float,
    gradientColors: List<Color> = listOf(
        Color(0xFF00E5FF),
        Color(0xFF00E676),
        Color(0xFFFFEA00),
        Color(0xFFFF0000)
    ),
    bottomTextColor : Color,
    bottomTextValue: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable{ }
            .heightIn(min = 160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                color = iconTint,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier
            .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            Column (modifier = Modifier.size(80.dp)){
                Text(
                    text = value,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = unit,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.size(40.dp))
            Column(modifier = Modifier.weight(1f)){
                MultiColorArcProgressBar(
                    targetProgress,
                    gradientColors = gradientColors
                )
                Text(
                    text = bottomTextValue,
                    color = bottomTextColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeStatCardPreview() {
    AppTheme {
        HomeStatCard(
            icon = Icons.Filled.DirectionsRun,
            iconTint = Color(0xFF00E5FF),
            label = "Steps",
            value = "8,432",
            unit = "Goal: 10,000",
            targetProgress = 0.84f,
            bottomTextColor = Color(0xFF00E676),
            bottomTextValue = "84%",
            onClick = { /* Preview click action */ }
        )
    }
}