package com.example.thecheezery.model.domain.data

import com.example.thecheezery.model.domain.Category
import com.example.thecheezery.model.domain.Product

object ProductRepository {

    fun getProducts(category: Category): List<Product> = when (category) {
        Category.HOT_DRINKS -> hotDrinks
        Category.COLD_DRINKS -> coldDrinks
        Category.SALTIES -> salties
        Category.SWEETS -> sweets
    }
}
