package com.its7ire.fitnesstracker.composable.bmi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CalculateButton(
    height: String,
    weight: String,
    viewModel: Unit,
    onCalculate: (Double?) -> Unit
) {

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            viewModel.saveBMI()

            val bmi = CalculateBmi(
                height = height,
                weight = weight
            )


            onCalculate(bmi)

        }
    ) {

        Text(
            text = "Calculate BMI"
        )

    }

}