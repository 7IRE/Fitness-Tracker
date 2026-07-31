package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.screen.PrimaryGreen
import com.its7ire.fitnesstracker.screen.SecondaryText


@Composable
fun ProfileInformation() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(92.dp)
                .border(
                    width = 1.5.dp,
                    color = PrimaryGreen,
                    shape = CircleShape
                )
                .padding(4.dp)
        ) {
//            Image(
//                painter = painterResource(id = R.drawable.profile_picture),
//                contentDescription = "Profile picture",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .clip(CircleShape)
//            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = "Alex Mercer",
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.WorkOutline,
                contentDescription = null,
                tint = SecondaryText,
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = "Pro Member",
                color = SecondaryText,
                fontSize = 14.sp
            )
        }
    }
}