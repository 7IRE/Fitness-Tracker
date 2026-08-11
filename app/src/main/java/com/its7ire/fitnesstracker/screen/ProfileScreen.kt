package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.its7ire.fitnesstracker.composable.profile.LogoutButton
import com.its7ire.fitnesstracker.composable.profile.ProfileInformation
import com.its7ire.fitnesstracker.composable.profile.ProfilePrefSec
import com.its7ire.fitnesstracker.composable.profile.ProfileStatSection
import com.its7ire.fitnesstracker.composable.profile.ProfileTopBar
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onSettingsClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileTopBar(
                onSettingsClick = onSettingsClick
            )

            Spacer(modifier = Modifier.height(22.dp))

            ProfileInformation()

            Spacer(modifier = Modifier.height(28.dp))

            ProfileStatSection()

            Spacer(modifier = Modifier.height(24.dp))

            ProfilePrefSec(
                onAppearanceClick = onAppearanceClick,
                onNotificationsClick = onNotificationsClick,
                onPrivacyClick = onPrivacyClick,
            )

            Spacer(modifier = Modifier.height(24.dp))

            LogoutButton(
                onClick = onLogoutClick
            )

            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    AppTheme() {
        ProfileScreen()
    }
}