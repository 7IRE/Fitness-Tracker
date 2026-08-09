package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.composable.home.bmi.BmiAgeField
import com.its7ire.fitnesstracker.composable.home.bmi.BmiHeightField
import com.its7ire.fitnesstracker.composable.home.bmi.BmiTopBar
import com.its7ire.fitnesstracker.composable.home.bmi.BmiWeightField
import com.its7ire.fitnesstracker.composable.home.bmi.CalculateButton
import com.its7ire.fitnesstracker.ui.theme.AppTheme
import com.its7ire.fitnesstracker.viewmodel.BmiUiState
import com.its7ire.fitnesstracker.viewmodel.BmiViewModel

@Composable
fun BMIScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier.safeContentPadding(),
    viewModel: BmiViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BMIScreenContent(
        uiState = uiState,
        onHeightChange = viewModel::onHeightChange,
        onWeightChange = viewModel::onWeightChange,
        onCalculateBmi = {
            viewModel.onCalculateBmi()
        },
        onNavigateBackHome = onNavigateBack,
        modifier = modifier
    )
}

@Composable
fun BMIScreenContent(
    uiState: BmiUiState,
    onHeightChange: (String) -> Unit,
    onWeightChange: (String) -> Unit,
    onCalculateBmi: () -> Unit,
    onNavigateBackHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            BmiTopBar(
                onBackClick = onNavigateBackHome
            )

            Spacer(modifier = Modifier.height(20.dp))
            BmiAgeField()

            Spacer(modifier = Modifier.height(60.dp))

            BmiWeightField(
                weight = uiState.weight,
                onWeightChange = onWeightChange
            )

            Spacer(modifier = Modifier.height(60.dp))

            BmiHeightField(
                height = uiState.height,
                onHeightChange = onHeightChange
            )

            Spacer(modifier = Modifier.height(40.dp))

            CalculateButton(
                height = uiState.height,
                weight = uiState.weight,
            ) {
                onCalculateBmi()
            }

            Spacer(modifier = Modifier.height(40.dp))

            val resultText = when {
                !uiState.isCalculated -> ""
                uiState.bmi == null -> "Invalid Input"
                else -> "BMI: %.2f".format(uiState.bmi)
            }

            if (resultText.isNotEmpty()) {
                Text(
                    text = resultText,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun BMIScreenPreview() {
    AppTheme(dynamicColor = false) {
        BMIScreenContent(
            uiState = BmiUiState(
                height = "180",
                weight = "75",
                bmi = 23.15,
                isCalculated = true
            ),
            onHeightChange = {},
            onWeightChange = {},
            onCalculateBmi = {},
            onNavigateBackHome = {}
        )
    }
}