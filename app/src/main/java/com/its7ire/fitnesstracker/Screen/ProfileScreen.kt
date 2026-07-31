package com.its7ire.fitnesstracker.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WorkOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val BackgroundColor = Color(0xFF0D100E)
private val CardColor = Color(0xFF101411)
private val BorderColor = Color(0xFF252A26)
private val PrimaryGreen = Color(0xFFA9C91D)
private val IconBackground = Color(0xFF202520)
private val SecondaryText = Color(0xFF9EA39A)
private val LogoutColor = Color(0xFFE89898)


@Preview
@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit = {},
    onAppearanceClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onPrivacyClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onBottomItemClick: (Int) -> Unit = {}
) {
    var selectedBottomItem by remember {
        mutableIntStateOf(3)
    }

    Scaffold(
        containerColor = BackgroundColor,
        bottomBar = {
            ProfileBottomNavigation(
                selectedItem = selectedBottomItem,
                onItemSelected = { index ->
                    selectedBottomItem = index
                    onBottomItemClick(index)
                }
            )
        }
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

            StatisticsSection()

            Spacer(modifier = Modifier.height(24.dp))

            PreferencesSection(
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

@Composable
private fun ProfileTopBar(
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

@Composable
private fun ProfileInformation() {
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

@Composable
private fun StatisticsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatisticCard(
            title = "STREAK",
            value = "14",
            suffix = "days",
            icon = Icons.Default.LocalFireDepartment,
            modifier = Modifier.weight(1f)
        )

        StatisticCard(
            title = "TOTAL VOL.",
            value = "12",
            suffix = "k",
            icon = Icons.Default.WorkOutline,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun StatisticCard(
    title: String,
    value: String,
    suffix: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .background(
                color = CardColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = BorderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = SecondaryText,
                modifier = Modifier.size(17.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                color = SecondaryText,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = value,
                color = PrimaryGreen,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                text = suffix,
                color = SecondaryText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 7.dp)
            )
        }
    }
}

@Composable
private fun PreferencesSection(
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

        PreferenceItem(
            title = "Change Appearance",
            icon = Icons.Default.DarkMode,
            onClick = onAppearanceClick
        )

        PreferenceDivider()

        PreferenceItem(
            title = "Notifications",
            icon = Icons.Default.Notifications,
            onClick = onNotificationsClick
        )

        PreferenceDivider()

        PreferenceItem(
            title = "Data & Privacy",
            icon = Icons.Default.Shield,
            onClick = onPrivacyClick
        )
    }
}

@Composable
private fun PreferenceItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable{
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = IconBackground,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Text(
            text = title,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = SecondaryText,
            modifier = Modifier.size(21.dp)
        )
    }
}

@Composable
private fun PreferenceDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
            .height(1.dp)
            .background(BorderColor)
    )
}

@Composable
private fun LogoutButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF3C3F3C),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable{
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = null,
            tint = LogoutColor,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(9.dp))

        Text(
            text = "Logout",
            color = LogoutColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
)

@Composable
private fun ProfileBottomNavigation(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    val navigationItems = listOf(
        BottomNavigationItem(
            title = "Dashboard",
            icon = Icons.Default.Dashboard
        ),
        BottomNavigationItem(
            title = "History",
            icon = Icons.Default.History
        ),
        BottomNavigationItem(
            title = "Coach",
            icon = Icons.Default.Psychology
        ),
        BottomNavigationItem(
            title = "Profile",
            icon = Icons.Default.Person
        )
    )

    NavigationBar(
        containerColor = Color(0xFF0F120F),
        tonalElevation = 0.dp,
        modifier = Modifier
            .height(76.dp)
            .border(
                width = 1.dp,
                color = BorderColor
            )
    ) {
        navigationItems.forEachIndexed { index, item ->
            val isSelected = selectedItem == index

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PrimaryGreen,
                    selectedTextColor = PrimaryGreen,
                    indicatorColor = Color(0xFF242B0E),
                    unselectedIconColor = SecondaryText,
                    unselectedTextColor = SecondaryText
                )
            )
        }
    }
}