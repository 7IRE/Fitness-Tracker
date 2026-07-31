package com.its7ire.fitnesstracker.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.its7ire.fitnesstracker.composable.profile.LogoutButton
import com.its7ire.fitnesstracker.composable.profile.ProfileInformation
import com.its7ire.fitnesstracker.composable.profile.ProfilePrefSec
import com.its7ire.fitnesstracker.composable.profile.ProfileStatSection
import com.its7ire.fitnesstracker.composable.profile.ProfileTopBar

private val BackgroundColor = Color(0xFF0D100E)
val CardColor = Color(0xFF101411)
val BorderColor = Color(0xFF252A26)
val PrimaryGreen = Color(0xFFA9C91D)
val IconBackground = Color(0xFF202520)
val SecondaryText = Color(0xFF9EA39A)
val LogoutColor = Color(0xFFE89898)



@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {

    Scaffold(
        containerColor = BackgroundColor,

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
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
                onPrivacyClick = onPrivacyClick
            )

            Spacer(modifier = Modifier.weight(1f))

            LogoutButton(
                onClick = onLogoutClick
            )

            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

















