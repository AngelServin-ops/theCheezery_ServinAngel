package com.example.thecheezery.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.thecheezery.model.domain.Category
import com.example.thecheezery.view.screens.Cart
import com.example.thecheezery.view.screens.Login
import com.example.thecheezery.view.screens.Menu
import com.example.thecheezery.view.screens.Products
import com.example.thecheezery.view.screens.Welcome
import com.example.thecheezery.viewmodel.CartViewModel



fun NavGraphBuilder.welcomeDestination(onGetStarted: () -> Unit) {
    composable<Routes.Welcome> {
        Welcome(onGetStarted = onGetStarted)
    }
}

fun NavGraphBuilder.loginDestination(onLoginSuccess: () -> Unit) {
    composable<Routes.Login> {
        Login(onLoginSuccess = onLoginSuccess)
    }
}

fun NavGraphBuilder.menuDestination(
    onCategoryClick: (Category) -> Unit,
    onCartClick: () -> Unit
) {
    composable<Routes.Menu> {
        Menu(onCategoryClick = onCategoryClick, onCartClick = onCartClick)
    }
}

fun NavGraphBuilder.productsDestination(cartViewModel: CartViewModel) {
    composable<Routes.Products> {
        Products(cartViewModel = cartViewModel)
    }
}

fun NavGraphBuilder.cartDestination(cartViewModel: CartViewModel) {
    composable<Routes.Cart> {
        Cart(cartViewModel = cartViewModel)
    }
}
