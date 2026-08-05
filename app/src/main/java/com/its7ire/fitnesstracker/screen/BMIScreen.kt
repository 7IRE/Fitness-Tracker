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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.composable.bmi.BmiAgeField
import com.its7ire.fitnesstracker.composable.bmi.BmiHeightField
import com.its7ire.fitnesstracker.composable.bmi.BmiTopBar
import com.its7ire.fitnesstracker.composable.bmi.BmiWeightField
import com.its7ire.fitnesstracker.composable.bmi.CalculateButton
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun BMIScreen(modifier: Modifier =
                       Modifier.safeContentPadding()) {
    var height by rememberSaveable {
        mutableStateOf("")
    }

    var weight by rememberSaveable {
        mutableStateOf("")
    }

    var bmi by rememberSaveable {
        mutableStateOf<Double?>(null)
    }
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            BmiTopBar()

            Spacer(modifier = Modifier.height(20.dp))
            BmiAgeField()

            Spacer(modifier = Modifier.height(60.dp))

            BmiWeightField(
                weight = weight,
                onWeightChange = {
                    weight = it
                }
            )

            Spacer(modifier = Modifier.height(60.dp))

            BmiHeightField(
                height = height,
                onHeightChange = {
                    height = it
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            CalculateButton(
                height = height,
                weight = weight
            ) { result ->

                bmi = result

            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = if (bmi == null) {
                    "Invalid Input"
                } else {
                    "BMI: %.2f".format(bmi)
                },
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PerformanceScreenPreview() {
    AppTheme(dynamicColor = false) {
        BMIScreen()
    }
}


