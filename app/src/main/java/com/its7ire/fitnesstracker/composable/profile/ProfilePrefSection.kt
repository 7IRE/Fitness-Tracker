package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import com.its7ire.fitnesstracker.ui.theme.neumorphic
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
    onUserInfoClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(20.dp)
    val dividerColor = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(cardShape)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            )
    ) {
        Text(
            text = "PREFERENCES",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.8.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        ProfilePrefItem(
            title = "User Info",
            icon = Icons.Default.Person,
            onClick = onUserInfoClick
        )

        HorizontalDivider(
            modifier = Modifier.padding(start = 36.dp),
            thickness = 1.dp,
            color = dividerColor
        )

        ProfilePrefItem(
            title = "Change Appearance",
            icon = Icons.Default.DarkMode,
            onClick = onAppearanceClick
        )

        HorizontalDivider(
            modifier = Modifier.padding(start = 36.dp),
            thickness = 1.dp,
            color = dividerColor
        )

        ProfilePrefItem(
            title = "Data & Privacy",
            icon = Icons.Default.Shield,
            onClick = onPrivacyClick
        )
    }
}
