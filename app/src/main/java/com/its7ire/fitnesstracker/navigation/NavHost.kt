package com.its7ire.fitnesstracker.navigation
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.its7ire.fitnesstracker.screen.BottomNavItem
import com.its7ire.fitnesstracker.screen.CoachScreen
import com.its7ire.fitnesstracker.screen.HomeScreen
import com.its7ire.fitnesstracker.screen.PerformanceScreen1
import com.its7ire.fitnesstracker.screen.ProfileScreen



enum class AppScreen(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Default.Home),
    Coach("Coach", Icons.Default.Star),
    History("History", Icons.AutoMirrored.Filled.List),
    Profile("Profile", Icons.Default.Person)
}

@Composable
fun FitnessApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.entries.firstOrNull {
        it.name == backStackEntry?.destination?.route
    } ?: AppScreen.Home

    Scaffold(
        bottomBar = {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppScreen.entries.forEach { screen ->
                        BottomNavItem(
                            icon = screen.icon,
                            label = screen.label,
                            selected = currentScreen == screen,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(screen.name) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = AppScreen.Home.name) {
                HomeScreen(modifier)
            }
            composable(route = AppScreen.Coach.name) {
                CoachScreen()
            }
            composable(route = AppScreen.History.name) {
                PerformanceScreen1()
            }
            composable(route = AppScreen.Profile.name) {
                ProfileScreen()
            }
        }
    }
}