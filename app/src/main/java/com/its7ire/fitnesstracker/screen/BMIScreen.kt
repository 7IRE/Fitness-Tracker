package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.its7ire.fitnesstracker.composable.home.bmi.BmiHeightField
import com.its7ire.fitnesstracker.composable.home.bmi.BmiTopBar
import com.its7ire.fitnesstracker.composable.home.bmi.BmiWeightField
import com.its7ire.fitnesstracker.composable.home.bmi.CalculateButton
import com.its7ire.fitnesstracker.composable.home.getBmiCategory
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            BmiTopBar(
                onBackClick = onNavigateBackHome
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BmiWeightField(
                    weight = uiState.weight,
                    onWeightChange = onWeightChange
                )

                Spacer(modifier = Modifier.height(24.dp))

                BmiHeightField(
                    height = uiState.height,
                    onHeightChange = onHeightChange
                )

                Spacer(modifier = Modifier.height(32.dp))

                CalculateButton(
                    height = uiState.height,
                    weight = uiState.weight
                ) {
                    onCalculateBmi()
                }

                if (uiState.isCalculated) {
                    Spacer(modifier = Modifier.height(28.dp))

                    if (uiState.bmi != null) {
                        val category = getBmiCategory(uiState.bmi)
                        Card(
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Your BMI",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "%.1f".format(uiState.bmi),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(category.color.copy(alpha = 0.2f))
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = category.label,
                                        color = category.color,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "Please enter valid height and weight",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun BMIScreenPreview() {
    AppTheme {
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