package com.its7ire.fitnesstracker.Screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- Color palette pulled from the design ----------
private val BgBlack = Color(0xFF0B0B0B)
private val CardDark = Color(0xFF1B1D16)
private val CardDarkAlt = Color(0xFF1A1A1A)
private val AccentLime = Color(0xFFC6EE3A)
private val AccentLimeDim = Color(0xFF6E7A2E)
private val TextGray = Color(0xFF9E9E9E)
private val TrackGray = Color(0xFF34381F)

@Preview
@Composable
fun PerformanceScreen() {
    Surface(color = BgBlack, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                TopBar()

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "TODAY",
                    color = AccentLime,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sun, Oct 24",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(20.dp))
                StepsCard(steps = 8432, goal = 10000)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ExerciseCard(minutes = 45, modifier = Modifier.weight(1f))
                    CaloriesCard(kcal = 320, modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(20.dp))
                CreateWorkoutButton()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(CardDarkAlt),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "Performance",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(CardDarkAlt),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun StepsCard(steps: Int, goal: Int) {
    val progress = (steps.toFloat() / goal.toFloat()).coerceIn(0f, 1f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.05f)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    listOf(CardDark, Color(0xFF14150F))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.62f),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val strokeWidth = 22.dp.toPx()
                val diameter = size.minDimension - strokeWidth
                val topLeft = androidx.compose.ui.geometry.Offset(
                    (size.width - diameter) / 2f,
                    (size.height - diameter) / 2f
                )
                val arcSize = Size(diameter, diameter)

                // background track
                drawArc(
                    color = TrackGray,
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
                // progress arc
                drawArc(
                    color = AccentLime,
                    startAngle = -90f,
                    sweepAngle = 360f * progress,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.DirectionsWalk,
                    contentDescription = "Steps",
                    tint = AccentLime,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "%,d".format(steps),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "/ %,d steps".format(goal),
                    color = TextGray,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
private fun StatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    label: String,
    value: String,
    unit: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(CardDark)
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
            Text(text = label, color = iconTint, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }

        Column {
            Text(text = value, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Text(text = unit, color = TextGray, fontSize = 12.sp)
        }
    }
}

@Composable
private fun ExerciseCard(minutes: Int, modifier: Modifier = Modifier) {
    StatCard(
        icon = Icons.Filled.Timer,
        iconTint = AccentLime,
        label = "Exercise",
        value = minutes.toString(),
        unit = "min active",
        modifier = modifier
    )
}

@Composable
private fun CaloriesCard(kcal: Int, modifier: Modifier = Modifier) {
    StatCard(
        icon = Icons.Filled.LocalFireDepartment,
        iconTint = Color(0xFFFF6B4A),
        label = "Calories",
        value = kcal.toString(),
        unit = "kcal burned",
        modifier = modifier
    )
}

@Composable
private fun CreateWorkoutButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(AccentLime),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Create workout",
            tint = Color.Black,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Create Your Own Workout",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}



@Composable
private fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean
) {
    val tint = if (selected) AccentLime else TextGray
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = tint,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

