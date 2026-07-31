package com.its7ire.fitnesstracker.composable.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.screen.BarData
import com.its7ire.fitnesstracker.screen.BarInactive
import com.its7ire.fitnesstracker.screen.CardDark
import com.its7ire.fitnesstracker.screen.Lime
import com.its7ire.fitnesstracker.screen.TextGray
import com.its7ire.fitnesstracker.screen.TextWhite

@Composable
fun HistoryAvgCard(average: String, badge: String, bars: List<BarData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardDark)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Daily Average",
                color = TextWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF232320))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(text = badge, color = TextWhite, fontSize = 12.sp)
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text = average,
                    color = TextWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        HistoryBarChart(bars)
    }
}

@Composable
fun HistoryBarChart(bars: List<BarData>) {
    val maxBarHeight = 110.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(maxBarHeight + 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        bars.forEach { bar ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.height(maxBarHeight + 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .height(maxBarHeight * bar.heightFraction)
                        .clip(RoundedCornerShape(6.dp))
                        .background(if (bar.isHighlighted) Lime else BarInactive)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = bar.label,
                    color = if (bar.isHighlighted) TextWhite else TextGray,
                    fontSize = 12.sp,
                    fontWeight = if (bar.isHighlighted) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}