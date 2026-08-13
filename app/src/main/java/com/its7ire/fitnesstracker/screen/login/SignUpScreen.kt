package com.its7ire.fitnesstracker.screen

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun SignUpScreen() {

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val pink = Color(0xFFFF72AA)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111111))
            .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(25.dp))

        Text(
            "FitPulse",
            color = Color(0xFFE75B9C),
            fontSize = 43.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(55.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF202020), RoundedCornerShape(22.dp))
                .padding(23.dp)
        ) {

            Text(
                "Create Account",
                color = Color.White,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(25.dp))

            InputField(
                "Full name",
                name,
                { name = it },
                Icons.Outlined.Person
            )

            InputField(
                "Email",
                email,
                { email = it },
                Icons.Outlined.Email,
                KeyboardType.Email
            )

            InputField(
                "Password",
                password,
                { password = it },
                Icons.Outlined.Lock,
                KeyboardType.Password,
                true
            )

            Spacer(Modifier.height(15.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(11.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = pink
                )
            ) {
                Text("Create Account")
            }

            Spacer(Modifier.height(25.dp))

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(Modifier.weight(1f))
                Text(
                    "  Or Continue With  ",
                    color = Color.Gray,
                    fontSize = 10.sp
                )
                HorizontalDivider(Modifier.weight(1f))
            }

            Spacer(Modifier.height(25.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF111111)
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("G   Google", fontSize = 16.sp)
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(  horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Text(
                "Already have an account? ",
                color = Color.White,
                fontSize = 13.sp
            )
            Text(
                "Login",
                color = pink,
                fontSize = 13.sp
            )
        }


    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboard: KeyboardType = KeyboardType.Text,
    password: Boolean = false
) {
    Text(
        text = label,
        color = Color(0xFFB7B7A9),
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    )

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        leadingIcon = {
            Icon(icon, contentDescription = null)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboard
        ),
        visualTransformation =
            if (password)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
        shape = RoundedCornerShape(10.dp)
    )

    Spacer(Modifier.height(10.dp))
}