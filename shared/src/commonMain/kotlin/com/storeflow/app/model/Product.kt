package com.storeflow.app.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Serializable
data class Product(
    val id: Long,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String, // We will use this for the list view
    val images: List<String>
)
