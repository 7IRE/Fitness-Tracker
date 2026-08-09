package com.its7ire.fitnesstracker.screen
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun LoginScreen(
//    onContinue: (name: String, age: Int, height: Double, weight: Double) -> Unit
//) {
//    var name by remember { mutableStateOf("") }
//    var age by remember { mutableStateOf("") }
//    var height by remember { mutableStateOf("") }
//    var weight by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 24.dp)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(60.dp))
//
//        Text(
//            text = "Welcome!",
//            fontSize = 32.sp,
//            fontWeight = FontWeight.Bold,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            text = "Let's set up your fitness profile",
//            fontSize = 16.sp,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
//
//        Spacer(modifier = Modifier.height(40.dp))
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Name") },
//            placeholder = { Text("Enter your name") },
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = age,
//            onValueChange = { age = it.filter { char -> char.isDigit() } },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Age") },
//            placeholder = { Text("Enter your age") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Number
//            ),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = height,
//            onValueChange = { height = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Height (cm)") },
//            placeholder = { Text("Enter your height") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal
//            ),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = weight,
//            onValueChange = { weight = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = { Text("Weight (kg)") },
//            placeholder = { Text("Enter your weight") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal
//            ),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        Button(
//            onClick = {
//                onContinue(
//                    name,
//                    age.toIntOrNull() ?: 0,
//                    height.toDoubleOrNull() ?: 0.0,
//                    weight.toDoubleOrNull() ?: 0.0
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(52.dp),
//            enabled = name.isNotBlank() &&
//                    age.isNotBlank() &&
//                    height.isNotBlank() &&
//                    weight.isNotBlank()
//        ) {
//            Text(
//                text = "Continue",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//    }
//}