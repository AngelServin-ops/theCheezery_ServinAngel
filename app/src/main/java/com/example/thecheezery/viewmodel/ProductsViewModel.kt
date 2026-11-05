package com.example.thecheezery.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.thecheezery.model.domain.Category
import com.example.thecheezery.model.domain.Product
import com.example.thecheezery.model.domain.data.ProductRepository
import com.example.thecheezery.navigation.Routes

class ProductsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val category: Category = savedStateHandle.toRoute<Routes.Products>().category
        .let { name -> Category.entries.firstOrNull { it.name == name } }
        ?: Category.HOT_DRINKS

    val title: String = category.title
    val products: List<Product> = ProductRepository.getProducts(category)
}
