package com.storeflow.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.storeflow.app.model.Product
import com.storeflow.app.viewmodel.ProductListState
import com.storeflow.app.viewmodel.ProductsViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen() {
    // 1. Inject the ViewModel (Koin magic!)
    val viewModel: ProductsViewModel = koinViewModel()

    // 2. Observe the State
    val state by viewModel.state.collectAsState()

    // 3. Switch on the state
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("StoreFlow") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (val currentState = state) {
                is ProductListState.Loading -> {
                    CircularProgressIndicator()
                }
                is ProductListState.Error -> {
                    Text(text = "Error: ${currentState.message}")
                }
                is ProductListState.Success -> {
                    ProductList(products = currentState.products)
                }
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    MaterialTheme {
        ProductItem(
            product = Product(
                id = 1,
                title = "iPhone 15 Pro",
                description = "The latest iPhone",
                price = 999.0,
                thumbnail = "",
                images = emptyList()
            )
        )
    }
}

@Preview
@Composable
fun ProductListPreview() {
    MaterialTheme {
        ProductList(
            products = listOf(
                Product(1, "iPhone 15 Pro", "", 999.0, "", emptyList()),
                Product(2, "MacBook Pro", "", 1999.0, "", emptyList()),
                Product(3, "iPad Pro", "", 799.0, "", emptyList())
            )
        )
    }
}
