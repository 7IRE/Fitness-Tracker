package com.its7ire.fitnesstracker.screen

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.DateUtils
import com.its7ire.fitnesstracker.composable.home.HomeBMICard
import com.its7ire.fitnesstracker.composable.home.HomeCaloriesCard
import com.its7ire.fitnesstracker.composable.home.HomeDistanceCard
import com.its7ire.fitnesstracker.composable.home.HomeStepCard
import com.its7ire.fitnesstracker.composable.home.HomeTopBar
import com.its7ire.fitnesstracker.composable.home.calories.calculateCaloriesFromSteps
import com.its7ire.fitnesstracker.composable.steps.StepSensor
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModel

@Composable
fun HomeScreen(
    onNavigateToBmi: () -> Unit,
    modifier: Modifier = Modifier,
    stepViewModel: StepViewModel = viewModel(),
    bmiViewModel: BmiViewModel = viewModel()
) {
    val context = LocalContext.current
    val steps by stepViewModel.steps.collectAsStateWithLifecycle()
    val bmiUiState by bmiViewModel.uiState.collectAsStateWithLifecycle()

    val stepSensor = remember {
        StepSensor(context) { sensorValue ->
            stepViewModel.processSensorValue(sensorValue)
        }
    }
    var hasPermission by remember {
        mutableStateOf(
            Build.VERSION.SDK_INT < Build.VERSION_CODES.Q ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACTIVITY_RECOGNITION
                    ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }
    LaunchedEffect(Unit) {
        if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
            !hasPermission
        ) {
            permissionLauncher.launch(
                Manifest.permission.ACTIVITY_RECOGNITION
            )
        }
    }

    DisposableEffect(hasPermission) {
        if (hasPermission) {
            stepSensor.startListening()
        }
        onDispose {
            stepViewModel.saveTodaySteps()
            stepSensor.stopListening()
        }
    }

    HomeScreenContent(
        steps = steps,
        bmiIndex = bmiUiState.bmi,
        weight = bmiUiState.weight,
        onNavigateToBmi = onNavigateToBmi,
        modifier = modifier
    )
}

@Composable
fun HomeScreenContent(
    steps: Int,
    bmiIndex: Double?,
    weight: String,
    onNavigateToBmi: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {

            item {
                Spacer(modifier = Modifier.height(12.dp))
                HomeTopBar()
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "TODAY",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = DateUtils.getCurrentDateShort(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
                HomeStepCard(
                    steps = steps,
                    goal = 10000
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HomeBMICard(
                    index = bmiIndex,
                    onClick = onNavigateToBmi
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HomeCaloriesCard(
                    kcal = calculateCaloriesFromSteps(
                        steps = steps,
                        weightKg = weight.toDoubleOrNull() ?: 0.0
                    )
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                HomeDistanceCard(
                    steps = steps
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreenContent(
            steps = 13499,
            bmiIndex = 22.4,
            weight = "68",
            onNavigateToBmi = {}
        )
    }
}