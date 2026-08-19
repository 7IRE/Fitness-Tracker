package com.its7ire.fitnesstracker.composable.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Button
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .clickable(onClick = onProfileClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Profile",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // Title
        Text(
            text = "FitPulse",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}