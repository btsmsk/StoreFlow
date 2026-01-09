package com.storeflow.app.di

import com.storeflow.app.viewmodel.ProductsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// A helper for iOS to retrieve dependencies from Koin
class ProductsViewModelComponent : KoinComponent {
    private val viewModel: ProductsViewModel by inject()
    fun provideProductsViewModel(): ProductsViewModel = viewModel
}