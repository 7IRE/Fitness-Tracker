package com.its7ire.fitnesstracker.navigation

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.its7ire.fitnesstracker.screen.BMIScreen
import com.its7ire.fitnesstracker.screen.BottomNavItem
import com.its7ire.fitnesstracker.screen.CoachScreen
import com.its7ire.fitnesstracker.screen.HomeScreen
import com.its7ire.fitnesstracker.screen.PerformanceScreen1
import com.its7ire.fitnesstracker.screen.ProfileScreen
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel
import com.its7ire.fitnesstracker.data.bmidata.BMIRepository
import com.its7ire.fitnesstracker.data.bmidata.AppDatabase
import com.its7ire.fitnesstracker.viewmodel.BmiViewModelFactory

enum class BottomNavScreen(val route: String, val label: String, val icon: ImageVector) {
    Home("home", "Home", Icons.Default.Home),
    Coach("coach", "Coach", Icons.Default.Star),
    History("history", "History", Icons.AutoMirrored.Filled.List),
    Profile("profile", "Profile", Icons.Default.Person)
}

object Routes {
    const val HOME = "home"
    const val COACH = "coach"
    const val HISTORY = "history"
    const val PROFILE = "profile"
    const val BMI = "bmi"
}


fun Context.findActivity(): ComponentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    return null
}
@Composable
fun FitnessApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Routes.HOME
    val context = LocalContext.current
    val activity = context.findActivity()
        ?: error("FitnessApp must be hosted inside a ComponentActivity")
    val database = AppDatabase.getDatabase(context)

    val repository = BMIRepository(
        database.bmiDao()
    )

    val sharedBmiViewModel: BmiViewModel = viewModel(
        factory = BmiViewModelFactory(repository)
    )

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
                    BottomNavScreen.entries.forEach { screen ->
                        BottomNavItem(
                            icon = screen.icon,
                            label = screen.label,
                            selected = currentRoute == screen.route,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(screen.route) {
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
            startDestination = Routes.HOME,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = Routes.HOME) {
                HomeScreen(
                    onNavigateToBmi = { navController.navigate(Routes.BMI) },
                    bmiViewModel = sharedBmiViewModel
                )
            }
            composable(route = Routes.COACH) {
                CoachScreen()
            }
            composable(route = Routes.HISTORY) {
                PerformanceScreen1()
            }
            composable(route = Routes.PROFILE) {
                ProfileScreen()
            }
            composable(route = Routes.BMI) {
                BMIScreen(
                    viewModel = sharedBmiViewModel,
                    onNavigateBack = { navController.navigate("home") {
                        popUpTo("bmi") {
                            inclusive = true
                        }
                    } }
                )
            }
        }
    }
}