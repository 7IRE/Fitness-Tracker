package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfilePrefSec(
    onAppearanceClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(12.dp)
    val borderColor = MaterialTheme.colorScheme.outlineVariant

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(cardShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = cardShape
            )
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            )
    ) {
        Text(
            text = "PREFERENCES",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfilePrefItem(
            title = "Change Appearance",
            icon = Icons.Default.DarkMode,
            onClick = onAppearanceClick
        )

        HorizontalDivider(
            modifier = Modifier.padding(start = 50.dp),
            thickness = 1.dp,
            color = borderColor
        )

        ProfilePrefItem(
            title = "Notifications",
            icon = Icons.Default.Notifications,
            onClick = onNotificationsClick
        )

        HorizontalDivider(
            modifier = Modifier.padding(start = 50.dp),
            thickness = 1.dp,
            color = borderColor
        )

        ProfilePrefItem(
            title = "Data & Privacy",
            icon = Icons.Default.Shield,
            onClick = onPrivacyClick
        )
    }
}