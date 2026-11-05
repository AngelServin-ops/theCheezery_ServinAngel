package com.example.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thecheezery.R
import com.example.thecheezery.ui.theme.Dusty_white
import com.example.thecheezery.ui.theme.Pinky
import com.example.thecheezery.viewmodel.LoginUiState
import com.example.thecheezery.viewmodel.LoginViewModel


@Composable
fun Login(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    onLoginSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LoginContent(
        uiState = uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = { if (viewModel.login()) onLoginSuccess() },
        modifier = modifier
    )
}

@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val emailError = uiState.emailError
    val passwordError = uiState.passwordError
    val fieldColors = TextFieldDefaults.colors(
        focusedContainerColor = Dusty_white,
        unfocusedContainerColor = Dusty_white,
        disabledContainerColor = Dusty_white
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))
        Image(
            painter = painterResource(id = R.drawable.grupo1),
            contentDescription = "Logo de The Cheezery",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(Modifier.height(48.dp))
        TextField(
            value = uiState.email,
            onValueChange = onEmailChange,
            placeholder = { Text("Correo electrónico") },
            singleLine = true,
            isError = emailError != null,
            supportingText = if (emailError != null) {
                { Text(emailError) }
            } else null,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = uiState.password,
            onValueChange = onPasswordChange,
            placeholder = { Text("Contraseña") },
            singleLine = true,
            isError = passwordError != null,
            supportingText = if (passwordError != null) {
                { Text(passwordError) }
            } else null,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
            colors = fieldColors
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Pinky)
        ) {
            Text(text = "Entrar", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    LoginContent(
        uiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {}
    )
}
