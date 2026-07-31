package com.its7ire.fitnesstracker.screen



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.composable.home.HomeCaloriesCard
import com.its7ire.fitnesstracker.composable.home.HomeCreateWorkoutButton
import com.its7ire.fitnesstracker.composable.home.HomeExerciseCard
import com.its7ire.fitnesstracker.composable.home.HomeTopBar
import com.its7ire.fitnesstracker.composable.home.HomeStepCard
private val BgBlack = Color(0xFF0B0B0B)
val CardDark = Color(0xFF1B1D16)
val CardDarkAlt = Color(0xFF1A1A1A)
val AccentLime = Color(0xFFC6EE3A)
val TextGray = Color(0xFF9E9E9E)
val TrackGray = Color(0xFF34381F)

@Preview
@Composable
fun PerformanceScreen() {
    Surface(modifier = Modifier.fillMaxSize(),
             color = BgBlack) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                HomeTopBar()

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
                HomeStepCard(steps = 8432, goal = 10000)

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    HomeExerciseCard(minutes = 45, modifier = Modifier.weight(1f))
                    HomeCaloriesCard(kcal = 320, modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(20.dp))
                HomeCreateWorkoutButton()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
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

