package com.example.thecheezery.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.thecheezery.ui.theme.TheCheezeryTheme
import com.example.thecheezery.viewmodel.CartViewModel

@Composable
fun RootApp() {
    TheCheezeryTheme {
        val navController = rememberNavController()

        val cartViewModel: CartViewModel = viewModel()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavHost(
                navController = navController,
                cartViewModel = cartViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
