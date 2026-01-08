package com.storeflow.app.di

import com.storeflow.app.network.StoreApi
import org.koin.core.context.startKoin
import org.koin.dsl.module

// Define the Shared Module
val appModule = module {
    // Declares a singleton of StoreApi
    single { StoreApi() }
}

// Function to initialize Koin (Called by Android App and iOS App)
fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
