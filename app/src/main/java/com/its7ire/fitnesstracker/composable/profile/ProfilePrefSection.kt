package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.screen.BorderColor
import com.its7ire.fitnesstracker.screen.CardColor
import com.its7ire.fitnesstracker.screen.SecondaryText

@Composable
fun ProfilePrefSec(
    onAppearanceClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onPrivacyClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = CardColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            )
    ) {
        Text(
            text = "PREFERENCES",
            color = SecondaryText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfilePrefItem(
            title = "Change Appearance",
            icon = Icons.Default.DarkMode,
            onClick = onAppearanceClick
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
                .height(1.dp)
                .background(BorderColor)
        )

        ProfilePrefItem(
            title = "Notifications",
            icon = Icons.Default.Notifications,
            onClick = onNotificationsClick
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
                .height(1.dp)
                .background(BorderColor)
        )

        ProfilePrefItem(
            title = "Data & Privacy",
            icon = Icons.Default.Shield,
            onClick = onPrivacyClick
        )
    }
}