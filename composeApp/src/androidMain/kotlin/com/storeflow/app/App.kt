package com.storeflow.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.storeflow.app.screens.ProductListScreen

@Composable
fun App() {
    MaterialTheme {
        ProductListScreen()
    }
}