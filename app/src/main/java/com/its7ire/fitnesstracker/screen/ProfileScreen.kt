package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.its7ire.fitnesstracker.data.userdata.UserProfile
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    user: UserProfile? = null,
    onUserInfoClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier.statusBarsPadding(),
        containerColor = androidx.compose.ui.graphics.Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            ProfileTopBar()

            Spacer(modifier = Modifier.height(20.dp))

            ProfileInformation(
                name = user?.name ?: "Alex Mercer",
                email = user?.email ?: ""
            )


//            ProfileStatSection()

            Spacer(modifier = Modifier.height(45.dp))

            ProfilePrefSec(
                onUserInfoClick = onUserInfoClick,
                onAppearanceClick = onAppearanceClick,
                onNotificationsClick = onNotificationsClick,
                onPrivacyClick = onPrivacyClick,
            )

            Spacer(modifier = Modifier.height(20.dp))

            LogoutButton(
                onClick = onLogoutClick
            )

            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreen()
    }
}