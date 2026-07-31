package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileTopBar(
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        Image(
//            painter = painterResource(id = null),
//            contentDescription = "Small profile picture",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .size(34.dp)
//                .clip(CircleShape)
//                .border(
//                    width = 1.dp,
//                    color = BorderColor,
//                    shape = CircleShape
//                )
//        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "Performance",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Settings",
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
                .clickable {
                    onSettingsClick()
                }
        )
    }
}