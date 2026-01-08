package com.storeflow.app.network

import com.storeflow.app.model.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class StoreApi {
    // 1. Configure the HTTP Client
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // robust against API changes
                prettyPrint = true
                isLenient = true
            })
        }
    }

    // 2. Define the endpoint
    private val baseUrl = "https://dummyjson.com"

    // 3. The Function to fetch data
    @Throws(Exception::class) // Useful for Swift error handling
    suspend fun getProducts(): ProductResponse {
        // GET https://dummyjson.com/products
        return client.get("$baseUrl/products").body()
    }
}
