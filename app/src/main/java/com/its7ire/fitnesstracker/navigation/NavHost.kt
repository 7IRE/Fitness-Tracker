package com.its7ire.fitnesstracker.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.its7ire.fitnesstracker.data.bmidata.AppDatabase
import com.its7ire.fitnesstracker.data.bmidata.BMIRepository
import com.its7ire.fitnesstracker.data.stepdata.DatabaseProvider
import com.its7ire.fitnesstracker.data.stepdata.StepRepository
import com.its7ire.fitnesstracker.screen.BMIScreen
import com.its7ire.fitnesstracker.screen.CoachScreen
import com.its7ire.fitnesstracker.screen.HistoryScreen
import com.its7ire.fitnesstracker.screen.HomeScreen
import com.its7ire.fitnesstracker.screen.ProfileScreen
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel
import com.its7ire.fitnesstracker.viewmodel.BmiViewModelFactory
import com.its7ire.fitnesstracker.viewmodel.StepViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModelFactory

object Routes {
    const val HOME = "home"
    const val COACH = "coach"
    const val HISTORY = "history"
    const val PROFILE = "profile"
    const val BMI = "bmi"
}

@Composable
fun FitnessApp(
    navController: NavHostController = rememberNavController(),
    onThemeChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Routes.HOME
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val stepDatabase = DatabaseProvider.getDatabase(context)

    val repository = BMIRepository(database.bmiDao())
    val stepRepository = StepRepository(stepDatabase.stepDao())

    val sharedBmiViewModel: BmiViewModel = viewModel(
        factory = BmiViewModelFactory(repository)
    )
    val stepViewModel: StepViewModel = viewModel(
        factory = StepViewModelFactory(stepRepository)
    )

    val showBottomBar = currentRoute in listOf(Routes.HOME, Routes.COACH, Routes.HISTORY, Routes.PROFILE)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = Routes.HOME) {
                HomeScreen(
                    onNavigateToBmi = { navController.navigate(Routes.BMI) },
                    bmiViewModel = sharedBmiViewModel,
                    stepViewModel = stepViewModel
                )
            }
            composable(route = Routes.COACH) {
                CoachScreen(stepViewModel = stepViewModel)
            }
            composable(route = Routes.HISTORY) {
                HistoryScreen()
            }
            composable(route = Routes.PROFILE) {
                ProfileScreen(onAppearanceClick = onThemeChanged)
            }
            composable(route = Routes.BMI) {
                BMIScreen(
                    viewModel = sharedBmiViewModel,
                    onNavigateBack = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.BMI) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        if (showBottomBar) {
            FloatingBottomNavBar(
                currentRoute = currentRoute,
                onNavigateToRoute = { targetRoute ->
                    if (targetRoute != currentRoute) {
                        navController.navigate(targetRoute) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}