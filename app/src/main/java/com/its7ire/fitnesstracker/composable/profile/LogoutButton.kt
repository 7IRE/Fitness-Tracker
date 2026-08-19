package com.its7ire.fitnesstracker.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonShape = RoundedCornerShape(16.dp)
    val logoutColor = MaterialTheme.colorScheme.error

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(buttonShape)
            .background(logoutColor.copy(alpha = 0.12f))
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = "Logout icon",
            tint = logoutColor,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Log Out",
            color = logoutColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}