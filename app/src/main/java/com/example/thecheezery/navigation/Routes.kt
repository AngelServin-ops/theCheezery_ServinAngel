package com.example.thecheezery.navigation

import kotlinx.serialization.Serializable


sealed interface Routes {

    @Serializable
    data object Welcome : Routes

    @Serializable
    data object Login : Routes

    @Serializable
    data object Menu : Routes

    @Serializable
    data class Products(val category: String) : Routes

    @Serializable
    data object Cart : Routes
}
