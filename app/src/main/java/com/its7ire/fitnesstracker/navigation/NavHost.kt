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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.its7ire.fitnesstracker.data.userdata.UserRepository
import com.its7ire.fitnesstracker.screen.BMIScreen
import com.its7ire.fitnesstracker.screen.CoachScreen
import com.its7ire.fitnesstracker.screen.DataPrivacyScreen
import com.its7ire.fitnesstracker.screen.HomeScreen
import com.its7ire.fitnesstracker.screen.HistoryScreen
import com.its7ire.fitnesstracker.screen.ProfileScreen
import com.its7ire.fitnesstracker.screen.UserInfoScreen
import com.its7ire.fitnesstracker.screen.login.LoginScreen
import com.its7ire.fitnesstracker.screen.login.ProfileSetupScreen
import com.its7ire.fitnesstracker.screen.login.SignUpScreen
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel
import com.its7ire.fitnesstracker.viewmodel.BmiViewModelFactory
import com.its7ire.fitnesstracker.viewmodel.StepViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModelFactory
import com.its7ire.fitnesstracker.viewmodel.HistoryViewModel
import com.its7ire.fitnesstracker.viewmodel.HistoryViewModelFactory
import com.its7ire.fitnesstracker.viewmodel.UserViewModel
import com.its7ire.fitnesstracker.viewmodel.UserViewModelFactory

object Routes {
    const val SIGN_UP = "signup"
    const val PROFILE_SETUP = "profile_setup"
    const val LOGIN = "login"
    const val HOME = "home"
    const val COACH = "coach"
    const val HISTORY = "history"
    const val PROFILE = "profile"
    const val BMI = "bmi"
    const val DATA_PRIVACY = "data_privacy"
    const val USER_INFO = "user_info"
}

@Composable
fun FitnessApp(
    navController: NavHostController = rememberNavController(),
    onThemeChanged: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val stepDatabase = DatabaseProvider.getDatabase(context)

    val repository = BMIRepository(database.bmiDao())
    val stepRepository = StepRepository(stepDatabase.stepDao())
    val userRepository = UserRepository(database.userDao())

    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(userRepository)
    )
    val isSessionReady by userViewModel.isSessionReady.collectAsStateWithLifecycle()
    val initialRoute by userViewModel.initialRoute.collectAsStateWithLifecycle()
    val currentUser by userViewModel.user.collectAsStateWithLifecycle()
    val loginError by userViewModel.loginError.collectAsStateWithLifecycle()

    val sharedBmiViewModel: BmiViewModel = viewModel(
        factory = BmiViewModelFactory(repository)
    )
    val stepViewModel: StepViewModel = viewModel(
        factory = StepViewModelFactory(stepRepository)
    )
    val historyViewModel: HistoryViewModel = viewModel(
        factory = HistoryViewModelFactory(stepRepository)
    )

    if (!isSessionReady || initialRoute == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
        return
    }

    val startRoute = initialRoute ?: Routes.SIGN_UP
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: startRoute

    val showBottomBar = currentRoute in listOf(Routes.HOME, Routes.COACH, Routes.HISTORY, Routes.PROFILE)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = startRoute,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = Routes.SIGN_UP) {
                SignUpScreen(
                    onSignUpSuccess = { name, email, pass ->
                        userViewModel.setSignUpDetails(name, email, pass)
                        navController.navigate(Routes.PROFILE_SETUP)
                    },
                    onNavigateToLogin = {
                        userViewModel.clearLoginError()
                        navController.navigate(Routes.LOGIN)
                    }
                )
            }
            composable(route = Routes.PROFILE_SETUP) {
                ProfileSetupScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onContinueClick = { age, weight, height, steps ->
                        userViewModel.completeProfileSetup(age, weight, height, steps) {
                            navController.navigate(Routes.HOME) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
            composable(route = Routes.LOGIN) {
                LoginScreen(
                    errorMessage = loginError,
                    onLoginClick = { email, pass ->
                        userViewModel.login(email, pass) {
                            navController.navigate(Routes.HOME) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    onSignUpClick = {
                        userViewModel.clearLoginError()
                        navController.navigate(Routes.SIGN_UP) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                        }
                    },
                    onClearError = {
                        userViewModel.clearLoginError()
                    }
                )
            }
            composable(route = Routes.HOME) {
                HomeScreen(
                    onNavigateToBmi = { navController.navigate(Routes.BMI) },
                    user = currentUser,
                    bmiViewModel = sharedBmiViewModel,
                    stepViewModel = stepViewModel
                )
            }
            composable(route = Routes.COACH) {
                CoachScreen(
                    user = currentUser,
                    stepViewModel = stepViewModel
                )
            }
            composable(route = Routes.HISTORY) {
                HistoryScreen(
                    viewModel = historyViewModel
                )
            }
            composable(route = Routes.PROFILE) {
                ProfileScreen(
                    user = currentUser,
                    onUserInfoClick = {
                        navController.navigate(Routes.USER_INFO)
                    },
                    onAppearanceClick = onThemeChanged,
                    onPrivacyClick = {
                        navController.navigate(Routes.DATA_PRIVACY)
                    },
                    onLogoutClick = {
                        userViewModel.logout {
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
            composable(route = Routes.USER_INFO) {
                UserInfoScreen(
                    user = currentUser,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onSaveUser = { updatedUser ->
                        userViewModel.updateUser(updatedUser)
                    }
                )
            }
            composable(route = Routes.DATA_PRIVACY) {
                DataPrivacyScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
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