package com.its7ire.fitnesstracker.screen

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
<<<<<<< Updated upstream
=======
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
>>>>>>> Stashed changes
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< Updated upstream
=======
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.composable.home.HomeBMICard
>>>>>>> Stashed changes
import com.its7ire.fitnesstracker.composable.home.HomeCaloriesCard
import com.its7ire.fitnesstracker.composable.home.HomeCreateWorkoutButton
import com.its7ire.fitnesstracker.composable.home.HomeExerciseCard
import com.its7ire.fitnesstracker.composable.home.HomeStepCard
import com.its7ire.fitnesstracker.composable.home.HomeTopBar
import com.its7ire.fitnesstracker.ui.theme.AppTheme
<<<<<<< Updated upstream

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
=======
import com.its7ire.fitnesstracker.utils.DateUtils
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel
import com.its7ire.fitnesstracker.viewmodel.StepViewModel
import androidx.compose.runtime.setValue



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

    val stepSensor = remember {
        StepSensor(context) { newSteps ->
            stepViewModel.updateSteps(newSteps)
        }
    }

    DisposableEffect(hasPermission) {

        if (hasPermission) {
            stepSensor.startListening()
        }

        onDispose {
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
>>>>>>> Stashed changes
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            HomeTopBar()

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

            Spacer(modifier = Modifier.height(20.dp))
            HomeStepCard(steps = 8432, goal = 10000)

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                HomeExerciseCard(minutes = 45, modifier = Modifier.weight(1f))
                HomeCaloriesCard(kcal = 320, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(20.dp))
            HomeCreateWorkoutButton()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun BottomNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val tint = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            color = tint,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme(dynamicColor = false) {
        HomeScreen()
    }
}