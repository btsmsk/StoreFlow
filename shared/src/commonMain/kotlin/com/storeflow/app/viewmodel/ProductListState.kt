package com.storeflow.app.viewmodel

import com.storeflow.app.model.Product

sealed interface ProductListState {
    data object Loading : ProductListState
    data class Success(val products: List<Product>) : ProductListState
    data class Error(val message: String) : ProductListState
}
