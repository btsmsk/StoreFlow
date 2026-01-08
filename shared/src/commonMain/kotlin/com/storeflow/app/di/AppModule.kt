package com.storeflow.app.di

import com.storeflow.app.network.StoreApi
import com.storeflow.app.viewmodel.ProductsViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel // This import will now work!
import org.koin.dsl.module

val appModule = module {
    single { StoreApi() }
    
    viewModel { ProductsViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
