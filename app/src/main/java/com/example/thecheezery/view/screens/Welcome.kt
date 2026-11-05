package com.example.thecheezery.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thecheezery.R
import com.example.thecheezery.ui.theme.Dusty_white
import com.example.thecheezery.ui.theme.Pinky
import com.example.thecheezery.ui.theme.Purple_grey


@Composable
fun Welcome(modifier: Modifier = Modifier, onGetStarted: () -> Unit = {}) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Purple_grey)
    ) {
        Image(
            painter = painterResource(id = R.drawable.the_cheezery),
            contentDescription = "Letrero de The Cheezery",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to The Cheezery",
                fontSize = 24.sp,
                color = Dusty_white
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Home of the most wonderful desserts ever seen (and tasted) by the human being.",
                modifier = Modifier.fillMaxWidth(),
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                color = Dusty_white
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = onGetStarted,
                colors = ButtonDefaults.buttonColors(containerColor = Pinky)
            ) {
                Text(text = "Get started!", color = Color.White)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WelcomePreview() {
    Welcome()
}
