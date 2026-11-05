package com.example.thecheezery.viewmodel

import androidx.lifecycle.ViewModel
import com.example.thecheezery.model.domain.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CartUiState(
    val items: List<Product> = emptyList(),
    val total: Float = 0f
) {
    val isEmpty: Boolean get() = items.isEmpty()
}

class CartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    fun addProduct(product: Product) {
        _uiState.update { state ->
            if (product in state.items) state
            else buildState(state.items + product)
        }
    }

    fun removeProduct(product: Product) {
        _uiState.update { state -> buildState(state.items - product) }
    }

    private fun buildState(items: List<Product>) = CartUiState(
        items = items,
        total = items.fold(0f) { acc, product -> acc + product.price }
    )
}
