package com.its7ire.fitnesstracker.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ProfileSetupScreen() {

    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }

    val pink = Color(0xFFFF80B0)
    val card = Color(0xFF1B2224)
    val bg = Color(0xFF111111)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(14.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text("←", color = Color.White, fontSize = 25.sp)

            Text(
                "Set Up Your Profile",
                modifier = Modifier.padding(start = 35.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(50.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(108.dp)
                    .background(card, CircleShape)
                    .border(1.dp, Color.DarkGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = null,
                    tint = Color(0xFF9B9B78),
                    modifier = Modifier.size(35.dp)
                )
            }
        }

        Text(
            "Upload Avatar",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFFB7A65F),
            fontSize = 11.sp
        )

        Spacer(Modifier.height(20.dp))

        Text("Physical Stats", color = Color.White, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(10.dp))

        StatBox("Age", age, { age = it }, "Years")

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            StatBox("Weight", weight, { weight = it }, "kg", Modifier.weight(1f))
            StatBox("Height", height, { height = it }, "cm", Modifier.weight(1f))
        }

        Spacer(Modifier.height(25.dp))

        Text("Goals", color = Color.White, fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(10.dp))

        StatBox("Daily Step Goal", steps, { steps = it }, "STEPS")

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = pink),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                "Continue  →",
                color = Color(0xFF58172F),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun StatBox(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = 12.dp)
            .background(
                Color(0xFF1B2224),
                RoundedCornerShape(10.dp)
            )
            .padding(14.dp)
    ) {

        Text(
            label,
            color = Color.Gray,
            fontSize = 10.sp
        )

        Row(verticalAlignment = Alignment.CenterVertically) {

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 27.sp,
                    color = Color(0xFF7D8DA8)
                )
            )

            Text(
                unit,
                color = Color(0xFFB7A65F),
                fontSize = 11.sp
            )
        }
    }
}