package com.example.thecheezery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.thecheezery.viewmodel.CartViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    cartViewModel: CartViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Welcome,
        modifier = modifier
    ) {
        welcomeDestination(
            onGetStarted = { navController.navigate(Routes.Login) }
        )
        loginDestination(
            onLoginSuccess = {
                navController.navigate(Routes.Menu) {
                    popUpTo<Routes.Welcome> { inclusive = true }
                }
            }
        )
        menuDestination(
            onCategoryClick = { category -> navController.navigate(Routes.Products(category.name)) },
            onCartClick = { navController.navigate(Routes.Cart) }
        )
        productsDestination(cartViewModel = cartViewModel)
        cartDestination(cartViewModel = cartViewModel)
    }
}
