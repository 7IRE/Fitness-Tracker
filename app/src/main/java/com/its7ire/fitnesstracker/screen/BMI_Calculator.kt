package com.its7ire.fitnesstracker.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.its7ire.fitnesstracker.composable.bmi_calculator.BmiAgeField
import com.its7ire.fitnesstracker.composable.bmi_calculator.BmiHeightField
import com.its7ire.fitnesstracker.composable.bmi_calculator.BmiTopBar
import com.its7ire.fitnesstracker.composable.bmi_calculator.BmiWeightField
import com.its7ire.fitnesstracker.composable.bmi_calculator.Calculatebmi
import com.its7ire.fitnesstracker.ui.theme.AppTheme

@Composable
fun BMI_Calculator(modifier: Modifier = Modifier) {
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
            BmiTopBar()

            Spacer(modifier = Modifier.height(20.dp))
            BmiAgeField()

            Spacer(modifier = Modifier.height(60.dp))
            BmiWeightField()

            Spacer(modifier = Modifier.height(60.dp))
            BmiHeightField()

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "BMI: '{Calculatebmi()}'",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun BMI_Calculator_Preview() {
    AppTheme(dynamicColor = false) {
        BMI_Calculator(modifier = Modifier)
    }
}
